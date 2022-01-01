package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.StatusModel;
import ucan.utils.DBConnection;

public class StatusDAO {
    private DBConnection connection;

    public StatusDAO() {

    }

    public void create(StatusModel status) {
        String sql = "INSERT INTO estado(nome) values(?)";
        try {
            connection = new DBConnection();
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

    public void update(StatusModel status) {
        String sql = "UPDATE estado SET nome = ? WHERE pk_estado = ?";
        try {
            connection = new DBConnection();
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

    public void delete(int statusId) {
        String sql = "DELETE FROM estado WHERE pk_estado = ?";
        try {
            connection = new DBConnection();
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

    public List<StatusModel> getAll() {
        String sql = "SELECT * FROM estado";

        List<StatusModel> statusList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                StatusModel status = new StatusModel();
                status.setStatusId(resultSet.getInt(1));
                status.setName(resultSet.getString(2));
                status.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

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

    public StatusModel getStatusById(int statusId) {
        String sql = "SELECT * FROM estado WHERE pk_estado = ?";

        try {
            StatusModel status = new StatusModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, statusId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                status.setStatusId(resultSet.getInt(1));
                status.setName(resultSet.getString(2));
                status.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
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
