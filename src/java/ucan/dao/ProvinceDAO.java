package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.ProvinceModel;
import ucan.utils.DBConnection;

public class ProvinceDAO {

    public ProvinceDAO() {

    }

    public static void create(ProvinceModel province, DBConnection connection) {
        String sql = "INSERT INTO provincia(nome, fk_pais) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, province.getName());
            ps.setInt(2, province.getCountryId());

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

    public static void update(ProvinceModel province, DBConnection connection) {
        String sql = "UPDATE provincia SET nome = ?, fk_pais = ? WHERE pk_provincia = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, province.getName());
            ps.setInt(2, province.getCountryId());            
            ps.setInt(3, province.getProvinceId());

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

    public static void delete(int provinceId, DBConnection connection) {
        String sql = "DELETE FROM provincia WHERE pk_provincia = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, provinceId);

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

    public static List<ProvinceModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM provincia";

        List<ProvinceModel> provinceList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ProvinceModel province = new ProvinceModel();
                province.setProvinceId(resultSet.getInt(1));
                province.setName(resultSet.getString(2));
                province.setCountryId(resultSet.getInt(3));
                province.setCreationDate(resultSet.getDate(4).toLocalDate());

                provinceList.add(province);
            }
            ps.close();
            resultSet.close();

            return provinceList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static ProvinceModel getProvinceById(int provinceId, DBConnection connection) {
        String sql = "SELECT * FROM provincia WHERE pk_provincia = ?";

        try {
            ProvinceModel province = new ProvinceModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, provinceId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                province.setProvinceId(resultSet.getInt(1));
                province.setName(resultSet.getString(2));
                province.setCountryId(resultSet.getInt(3));
                province.setCreationDate(resultSet.getDate(4).toLocalDate());
            }

            ps.close();
            resultSet.close();
            return province;

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
