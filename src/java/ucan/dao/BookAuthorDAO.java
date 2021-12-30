package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookAuthorModel;
import ucan.utils.DBConnection;

public class BookAuthorDAO {

    public BookAuthorDAO() {
    }

    public static void create(BookAuthorModel bookAuthor, DBConnection connection) {
        String sql = "INSERT INTO livro_autor(fk_livro, fk_autor) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookAuthor.getBookId());
            ps.setInt(2, bookAuthor.getAuthorId());

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

    public static void update(BookAuthorModel bookAuthor, DBConnection connection) {
        String sql = "UPDATE livro_autor SET fk_livro = ?, fk_autor = ? WHERE pk_livro_autor = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, bookAuthor.getBookId());
            ps.setInt(2, bookAuthor.getAuthorId());
            ps.setInt(3, bookAuthor.getBookAuthorId());

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

    public static void delete(int bookAuthorId, DBConnection connection) {
        String sql = "DELETE FROM livro_autor WHERE pk_livro_autor = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookAuthorId);

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

    public static List<BookAuthorModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM livro_autor";

        List<BookAuthorModel> bookAuthorList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookAuthorModel location = new BookAuthorModel();
                location.setBookAuthorId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setAuthorId(resultSet.getInt(3));
                location.setCreationDate(resultSet.getDate(4).toLocalDate());

                bookAuthorList.add(location);
            }
            ps.close();
            resultSet.close();

            return bookAuthorList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static BookAuthorModel getBookAuthorById(int locationId, DBConnection connection) {
        String sql = "SELECT * FROM livro_autor WHERE pk_livro_autor = ?";

        try {
            BookAuthorModel location = new BookAuthorModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, locationId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                location.setBookAuthorId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setAuthorId(resultSet.getInt(3));
                location.setCreationDate(resultSet.getDate(4).toLocalDate());
            }

            ps.close();
            resultSet.close();
            return location;

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
