package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.GenderModel;
import ucan.utils.DBConnection;

public class GenderDAO {
    private DBConnection connection;

    public GenderDAO() {

    }

    public void create(GenderModel gender) {
        String sql = "INSERT INTO sexo(nome) values(?)";
        try {
            connection = new DBConnection();
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

    public void update(GenderModel gender) {
        String sql = "UPDATE sexo SET nome = ? WHERE pk_sexo = ?";
        try {
            connection = new DBConnection();
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

    public void delete(int genderId) {
        String sql = "DELETE FROM sexo WHERE pk_sexo = ?";
        try {
            connection = new DBConnection();
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

    public  List<GenderModel> getAll() {
        String sql = "SELECT * FROM sexo";

        List<GenderModel> genderList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                GenderModel gender = new GenderModel();
                gender.setGenderId(resultSet.getInt(1));
                gender.setName(resultSet.getString(2));
                gender.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

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

    public GenderModel getGenderById(int genderId) {
        String sql = "SELECT * FROM sexo WHERE pk_sexo = ?";

        try {
            GenderModel gender = new GenderModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, genderId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                gender.setGenderId(resultSet.getInt(1));
                gender.setName(resultSet.getString(2));
                gender.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
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
