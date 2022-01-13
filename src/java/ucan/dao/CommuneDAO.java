package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.CommuneModel;
import ucan.conection.DBConnection;

public class CommuneDAO {

    public CommuneDAO() {

    }

    public void create(CommuneModel commune, DBConnection connection) {
        String sql = "INSERT INTO comuna(nome, fk_municipio) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, commune.getName());
            ps.setInt(2, commune.getMunicipalityId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(CommuneModel commune, DBConnection connection) {
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
        }
    }

    public void delete(int communeId, DBConnection connection) {
        String sql = "DELETE FROM comuna WHERE pk_comuna = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, communeId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<CommuneModel> getAll(DBConnection connection) {
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
                commune.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                communeList.add(commune);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return communeList;
    }

    public CommuneModel getCommuneById(int communeId, DBConnection connection) {
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
                commune.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return commune;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<CommuneModel> getCommunesByMunicipalityId(int municipalityId, DBConnection connection) {
        String sql = "SELECT * FROM comuna WHERE fk_municipio = " + municipalityId;

        List<CommuneModel> communeList = new ArrayList<>();

        try {
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
        }
        return communeList;
    }
}
