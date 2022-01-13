package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.ClassificationModel;
import ucan.conection.DBConnection;

public class ClassificationDAO {

    public ClassificationDAO() {

    }

    public void create(ClassificationModel classification, DBConnection connection) {
        String sql = "INSERT INTO classificacao(nome) values(?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, classification.getName());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ClassificationModel classification, DBConnection connection) {
        String sql = "UPDATE classificacao SET nome = ? WHERE pk_classificacao = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, classification.getName());
            ps.setInt(2, classification.getClassificationId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int classificationId, DBConnection connection) {
        String sql = "DELETE FROM classificacao WHERE pk_classificacao = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, classificationId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ClassificationModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM classificacao";

        List<ClassificationModel> classificationList = new ArrayList<>();

        try {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return classificationList;
    }

    public ClassificationModel getClassificationById(int classificationId, DBConnection connection) {
        String sql = "SELECT * FROM classificacao WHERE pk_classificacao = ?";

        try {
            ClassificationModel classification = new ClassificationModel();
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
        }
        return null;
    }
}
