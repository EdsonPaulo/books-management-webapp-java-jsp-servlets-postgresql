package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.ClassificationModel;
import ucan.utils.DBConnection;

public class ClassificationDAO {
    private DBConnection connection;

    public ClassificationDAO() {

    }

    public void create(ClassificationModel classification) {
        String sql = "INSERT INTO classificacao(nome) values(?)";
        try {
            connection = new DBConnection();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, classification.getName());

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

    public void update(ClassificationModel classification) {
        String sql = "UPDATE classificacao SET nome = ? WHERE pk_classificacao = ?";
        try {
            connection = new DBConnection();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, classification.getName());
            ps.setInt(2, classification.getClassificationId());

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

    public void delete(int classificationId) {
        String sql = "DELETE FROM classificacao WHERE pk_classificacao = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, classificationId);

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

    public List<ClassificationModel> getAll() {
        String sql = "SELECT * FROM classificacao";

        List<ClassificationModel> classificationList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ClassificationModel classification = new ClassificationModel();
                classification.setClassificationId(resultSet.getInt(1));
                classification.setName(resultSet.getString(2));
                classification.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

                classificationList.add(classification);
            }
            ps.close();
            resultSet.close();

            return classificationList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public ClassificationModel getClassificationById(int classificationId) {
        String sql = "SELECT * FROM classificacao WHERE pk_classificacao = ?";

        try {
            connection = new DBConnection();
            ClassificationModel classification = new ClassificationModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, classificationId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                classification.setClassificationId(resultSet.getInt(1));
                classification.setName(resultSet.getString(2));
                classification.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return classification;

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
