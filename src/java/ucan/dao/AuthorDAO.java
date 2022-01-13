package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.AuthorModel;
import ucan.conection.DBConnection;

public class AuthorDAO {

    public AuthorDAO() {

    }

    public void create(AuthorModel author, DBConnection connection) {
        String sql = "INSERT INTO autor(fk_pessoa) values(?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, author.getPersonId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(AuthorModel author,DBConnection connection) {
        String sql = "UPDATE autor SET fk_pessoa = ? WHERE pk_autor = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, author.getPersonId());
            ps.setInt(2, author.getAuthorId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int authorId,DBConnection connection) {
        String sql = "DELETE FROM autor WHERE pk_autor = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, authorId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AuthorModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM autor";
        List<AuthorModel> readerList = new ArrayList<>();

        try {
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
        }
        return readerList;
    }

    public AuthorModel getAuthorById(int authorId,DBConnection connection) {
        String sql = "SELECT * FROM autor WHERE pk_autor = ?";

        try {
            AuthorModel author = new AuthorModel();

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
        }
        return null;
    }
}
