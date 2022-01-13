package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.CommuneModel;
import ucan.conection.DBConnection;

public class CommuneDAO {

    private DBConnection connection;

    public CommuneDAO() {

    }

    public void create(CommuneModel commune) {
        String sql = "INSERT INTO comuna(nome, fk_municipio) values(?, ?)";
        try {
            connection = new DBConnection();
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

    public void update(CommuneModel commune) {
        String sql = "UPDATE comuna SET nome = ?, fk_municipio = ? WHERE pk_comuna = ?";
        try {
            connection = new DBConnection();
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

    public void delete(int communeId) {
        String sql = "DELETE FROM comuna WHERE pk_comuna = ?";
        try {
            connection = new DBConnection();
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

    public List<CommuneModel> getAll() {
        String sql = "SELECT * FROM comuna";

        List<CommuneModel> communeList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                CommuneModel commune = new CommuneModel();
                commune.setCommuneId(resultSet.getInt(1));
                commune.setName(resultSet.getString(2));
                commune.setMunicipalityId(resultSet.getInt(3));
                commune.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                communeList.add(commune);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return communeList;
    }

    public CommuneModel getCommuneById(int communeId) {
        String sql = "SELECT * FROM comuna WHERE pk_comuna = ?";

        try {
            CommuneModel commune = new CommuneModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, communeId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                commune.setCommuneId(resultSet.getInt(1));
                commune.setName(resultSet.getString(2));
                commune.setMunicipalityId(resultSet.getInt(3));
                commune.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());
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
    
      public List<CommuneModel> getCommunesByMunicipalityId(int municipalityId ) {
        String sql = "SELECT * FROM comuna WHERE fk_municipio = " + municipalityId;

        List<CommuneModel> communeList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                CommuneModel commune = new CommuneModel();
                commune.setCommuneId(resultSet.getInt(1));
                commune.setName(resultSet.getString(2));
                commune.setMunicipalityId(resultSet.getInt(3));
                commune.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                communeList.add(commune);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return communeList;
    }
}
