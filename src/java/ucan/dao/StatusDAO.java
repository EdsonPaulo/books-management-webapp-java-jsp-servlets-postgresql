package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.StatusModel;
import ucan.utils.DBConnection;

public class StatusDAO {

    public StatusDAO() {

    }

    public static void create(StatusModel status, DBConnection connection) {
        String sql = "INSERT INTO estado(nome) values(?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, status.getName());

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

    public static void update(StatusModel status, DBConnection connection) {
        String sql = "UPDATE estado SET nome = ? WHERE pk_estado = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, status.getName());
            ps.setInt(2, status.getStatusId());

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

    public static void delete(int statusId, DBConnection connection) {
        String sql = "DELETE FROM estado WHERE pk_estado = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, statusId);

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

    public static List<StatusModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM estado";

        List<StatusModel> statusList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                StatusModel status = new StatusModel();
                status.setStatusId(resultSet.getInt(1));
                status.setName(resultSet.getString(2));
                status.setCreationDate(resultSet.getDate(3).toLocalDate());

                statusList.add(status);
            }
            ps.close();
            resultSet.close();

            return statusList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static StatusModel getStatusById(int statusId, DBConnection connection) {
        String sql = "SELECT * FROM estado WHERE pk_estado = ?";

        try {
            StatusModel status = new StatusModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, statusId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                status.setStatusId(resultSet.getInt(1));
                status.setName(resultSet.getString(2));
                status.setCreationDate(resultSet.getDate(3).toLocalDate());
            }

            ps.close();
            resultSet.close();
            return status;

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
