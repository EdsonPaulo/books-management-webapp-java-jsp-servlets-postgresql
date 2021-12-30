package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.MunicipalityModel;
import ucan.utils.DBConnection;

public class MunicipalityDAO {

    public MunicipalityDAO() {

    }

    public static void create(MunicipalityModel municipality, DBConnection connection) {
        String sql = "INSERT INTO municipio(nome, fk_provincia) values(?, ?)";
        try {
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

    public static void update(MunicipalityModel municipality, DBConnection connection) {
        String sql = "UPDATE municipio SET nome = ?, fk_provincia = ? WHERE pk_municipio = ?";
        try {
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

    public static void delete(int municipalityId, DBConnection connection) {
        String sql = "DELETE FROM municipio WHERE pk_municipio = ?";
        try {
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

    public static List<MunicipalityModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM municipio";

        List<MunicipalityModel> municipalityList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                MunicipalityModel municipality = new MunicipalityModel();
                municipality.setMunicipalityId(resultSet.getInt(1));
                municipality.setName(resultSet.getString(2));
                municipality.setProvinceId(resultSet.getInt(3));
                municipality.setCreationDate(resultSet.getDate(4).toLocalDate());

                municipalityList.add(municipality);
            }
            ps.close();
            resultSet.close();

            return municipalityList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static MunicipalityModel getMunicipalityById(int municipalityId, DBConnection connection) {
        String sql = "SELECT * FROM municipio WHERE pk_municipio = ?";

        try {
            MunicipalityModel municipality = new MunicipalityModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, municipalityId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                municipality.setMunicipalityId(resultSet.getInt(1));
                municipality.setName(resultSet.getString(2));
                municipality.setProvinceId(resultSet.getInt(3));
                municipality.setCreationDate(resultSet.getDate(4).toLocalDate());
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
}
