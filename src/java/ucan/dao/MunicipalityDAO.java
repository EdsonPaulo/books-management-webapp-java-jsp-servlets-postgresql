package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.MunicipalityModel;
import ucan.conection.DBConnection;

public class MunicipalityDAO {

    private DBConnection connection;

    public MunicipalityDAO() {

    }

    public void create(MunicipalityModel municipality) {
        String sql = "INSERT INTO municipio(nome, fk_provincia) values(?, ?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, municipality.getName());
            ps.setInt(2, municipality.getProvinceId());

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

    public void update(MunicipalityModel municipality) {
        String sql = "UPDATE municipio SET nome = ?, fk_provincia = ? WHERE pk_municipio = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, municipality.getName());
            ps.setInt(2, municipality.getProvinceId());
            ps.setInt(3, municipality.getMunicipalityId());

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

    public void delete(int municipalityId) {
        String sql = "DELETE FROM municipio WHERE pk_municipio = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, municipalityId);

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

    public List<MunicipalityModel> getAll() {
        String sql = "SELECT * FROM municipio";

        List<MunicipalityModel> municipalityList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                MunicipalityModel municipality = new MunicipalityModel();
                municipality.setMunicipalityId(resultSet.getInt(1));
                municipality.setName(resultSet.getString(2));
                municipality.setProvinceId(resultSet.getInt(3));
                municipality.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                municipalityList.add(municipality);
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
        return municipalityList;
    }

    public MunicipalityModel getMunicipalityById(int municipalityId) {
        String sql = "SELECT * FROM municipio WHERE pk_municipio = ?";

        try {
            MunicipalityModel municipality = new MunicipalityModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, municipalityId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                municipality.setMunicipalityId(resultSet.getInt(1));
                municipality.setName(resultSet.getString(2));
                municipality.setProvinceId(resultSet.getInt(3));
                municipality.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return municipality;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return null;
    }
    
    
       public List<MunicipalityModel> getMunicipalitiesByProvinceId(int provinceId) {
        String sql = "SELECT * FROM municipio WHERE fk_provincia = " + provinceId;

        List<MunicipalityModel> municipalityList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                MunicipalityModel municipality = new MunicipalityModel();
                municipality.setMunicipalityId(resultSet.getInt(1));
                municipality.setName(resultSet.getString(2));
                municipality.setProvinceId(resultSet.getInt(3));
                municipality.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                municipalityList.add(municipality);
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
        return municipalityList;
    }
}
