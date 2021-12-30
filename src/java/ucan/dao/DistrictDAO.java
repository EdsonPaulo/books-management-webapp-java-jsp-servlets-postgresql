package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.DistrictModel;
import ucan.utils.DBConnection;

public class DistrictDAO {

    public DistrictDAO() {

    }

    public static void create(DistrictModel district, DBConnection connection) {
        String sql = "INSERT INTO bairro(nome, fk_comuna) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, district.getName());
            ps.setInt(2, district.getCommuneId());

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

    public static void update(DistrictModel district, DBConnection connection) {
        String sql = "UPDATE bairro SET nome = ?, fk_comuna = ? WHERE pk_bairro = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, district.getName());
            ps.setInt(2, district.getCommuneId());
            ps.setInt(3, district.getDistrictId());

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

    public static void delete(int districtId, DBConnection connection) {
        String sql = "DELETE FROM bairro WHERE pk_bairro = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, districtId);

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

    public static List<DistrictModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM bairro";

        List<DistrictModel> districtList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                DistrictModel district = new DistrictModel();
                district.setDistrictId(resultSet.getInt(1));
                district.setName(resultSet.getString(2));
                district.setCommuneId(resultSet.getInt(3));
                district.setCreationDate(resultSet.getDate(4).toLocalDate());

                districtList.add(district);
            }
            ps.close();
            resultSet.close();

            return districtList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static DistrictModel getDistrictById(int districtId, DBConnection connection) {
        String sql = "SELECT * FROM bairro WHERE pk_bairro = ?";

        try {
            DistrictModel district = new DistrictModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, districtId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                district.setDistrictId(resultSet.getInt(1));
                district.setName(resultSet.getString(2));
                district.setCommuneId(resultSet.getInt(3));
                district.setCreationDate(resultSet.getDate(4).toLocalDate());
            }

            ps.close();
            resultSet.close();
            return district;

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
