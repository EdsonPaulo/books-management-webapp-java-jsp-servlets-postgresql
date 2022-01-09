package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.AuthorModel;
import ucan.conection.DBConnection;

public class AuthorDAO {

    private DBConnection connection;

    public AuthorDAO() {

    }

    public void create(AuthorModel author) {
        String sql = "INSERT INTO autor(fk_pessoa) values(?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, author.getPersonId());

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

    public void update(AuthorModel author) {
        String sql = "UPDATE autor SET fk_pessoa = ? WHERE pk_autor = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, author.getPersonId());
            ps.setInt(2, author.getAuthorId());

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

    public void delete(int authorId) {
        String sql = "DELETE FROM autor WHERE pk_autor = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, authorId);

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

    public List<AuthorModel> getAll() {
        String sql = "SELECT * FROM autor";
        List<AuthorModel> readerList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                AuthorModel reader = new AuthorModel();
                reader.setAuthorId(resultSet.getInt(1));
                reader.setPersonId(resultSet.getInt(2));
                reader.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

                readerList.add(reader);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return readerList;
    }

    public AuthorModel getAuthorById(int authorId) {
        String sql = "SELECT * FROM autor WHERE pk_autor = ?";

        try {
            AuthorModel author = new AuthorModel();
            connection = new DBConnection();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, authorId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                author.setAuthorId(resultSet.getInt(1));
                author.setPersonId(resultSet.getInt(2));
                author.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return author;

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
