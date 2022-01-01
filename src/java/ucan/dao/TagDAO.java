package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.TagModel;
import ucan.utils.DBConnection;

public class TagDAO {
    private DBConnection connection;

    public TagDAO() {

    }

    public void create(TagModel tag) {
        String sql = "INSERT INTO descritores(nome) values(?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, tag.getName());

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

    public void update(TagModel tag) {
        String sql = "UPDATE descritores SET nome = ? WHERE pk_descritores = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, tag.getName());            
            ps.setInt(2, tag.getTagId());

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

    public void delete(int tagId) {
        String sql = "DELETE FROM descritores WHERE pk_descritores = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, tagId);

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

    public List<TagModel> getAll() {
        String sql = "SELECT * FROM descritores";

        List<TagModel> tagList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                TagModel tag = new TagModel();
                tag.setTagId(resultSet.getInt(1));
                tag.setName(resultSet.getString(2));
                tag.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

                tagList.add(tag);
            }
            ps.close();
            resultSet.close();

            return tagList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public TagModel getTagById(int tagId) {
        String sql = "SELECT * FROM descritores WHERE pk_descritores = ?";

        try {
            TagModel tag = new TagModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, tagId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                tag.setTagId(resultSet.getInt(1));
                tag.setName(resultSet.getString(2));
                tag.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return tag;

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
