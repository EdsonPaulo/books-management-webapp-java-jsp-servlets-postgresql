package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.CommuneModel;
import ucan.utils.DBConnection;

public class CommuneDAO {

    public CommuneDAO() {

    }

    public static void create(CommuneModel commune, DBConnection connection) {
        String sql = "INSERT INTO comuna(nome, fk_municipio) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, commune.getName());
            ps.setInt(2, commune.getMunicipalityId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static void update(CommuneModel commune, DBConnection connection) {
        String sql = "UPDATE comuna SET nome = ?, fk_municipio = ? WHERE pk_comuna = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, commune.getName());
            ps.setInt(2, commune.getMunicipalityId());
            ps.setInt(3, commune.getCommuneId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static void delete(int communeId, DBConnection connection) {
        String sql = "DELETE FROM comuna WHERE pk_comuna = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, communeId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static List<CommuneModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM comuna";

        List<CommuneModel> communeList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                CommuneModel commune = new CommuneModel();
                commune.setCommuneId(resultSet.getInt(1));
                commune.setName(resultSet.getString(2));
                commune.setMunicipalityId(resultSet.getInt(3));
                commune.setCreationDate(resultSet.getDate(4).toLocalDate());

                communeList.add(commune);
            }
            ps.close();
            resultSet.close();

            return communeList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static CommuneModel getCommuneById(int communeId, DBConnection connection) {
        String sql = "SELECT * FROM comuna WHERE pk_comuna = ?";

        try {
            CommuneModel commune = new CommuneModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, communeId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                commune.setCommuneId(resultSet.getInt(1));
                commune.setName(resultSet.getString(2));
                commune.setMunicipalityId(resultSet.getInt(3));
                commune.setCreationDate(resultSet.getDate(4).toLocalDate());
            }

            ps.close();
            resultSet.close();
            return commune;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return null;
    }
}
