package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.GenderModel;
import ucan.utils.DBConnection;

public class GenderDAO {

    public GenderDAO() {

    }

    public static void create(GenderModel gender, DBConnection connection) {
        String sql = "INSERT INTO sexo(nome) values(?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, gender.getName());

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

    public static void update(GenderModel gender, DBConnection connection) {
        String sql = "UPDATE sexo SET nome = ? WHERE pk_sexo = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, gender.getName());
            ps.setInt(2, gender.getGenderId());

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

    public static void delete(int genderId, DBConnection connection) {
        String sql = "DELETE FROM sexo WHERE pk_sexo = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, genderId);

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

    public static List<GenderModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM sexo";

        List<GenderModel> genderList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                GenderModel gender = new GenderModel();
                gender.setGenderId(resultSet.getInt(1));
                gender.setName(resultSet.getString(2));
                gender.setCreationDate(resultSet.getDate(3).toLocalDate());

                genderList.add(gender);
            }
            ps.close();
            resultSet.close();

            return genderList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static GenderModel getGenderById(int genderId, DBConnection connection) {
        String sql = "SELECT * FROM sexo WHERE pk_sexo = ?";

        try {
            GenderModel gender = new GenderModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, genderId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                gender.setGenderId(resultSet.getInt(1));
                gender.setName(resultSet.getString(2));
                gender.setCreationDate(resultSet.getDate(3).toLocalDate());
            }

            ps.close();
            resultSet.close();
            return gender;

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
