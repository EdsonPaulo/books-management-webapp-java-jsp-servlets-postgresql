package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookPublisherModel;
import ucan.utils.DBConnection;

public class BookPublisherDAO {

    public BookPublisherDAO() {
    }

    public static void create(BookPublisherModel bookPublisher, DBConnection connection) {
        String sql = "INSERT INTO livro_editora(fk_livro, fk_editora) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookPublisher.getBookId());
            ps.setInt(2, bookPublisher.getPublisherId());

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

    public static void update(BookPublisherModel bookPublisher, DBConnection connection) {
        String sql = "UPDATE livro_editora SET fk_livro = ?, fk_editora = ? WHERE pk_livro_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, bookPublisher.getBookId());
            ps.setInt(2, bookPublisher.getPublisherId());
            ps.setInt(3, bookPublisher.getBookPublisherId());

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

    public static void delete(int bookPublisherId, DBConnection connection) {
        String sql = "DELETE FROM livro_editora WHERE pk_livro_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookPublisherId);

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

    public static List<BookPublisherModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM livro_editora";

        List<BookPublisherModel> bookPublisherList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookPublisherModel location = new BookPublisherModel();
                location.setBookPublisherId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setPublisherId(resultSet.getInt(3));
                location.setCreationDate(resultSet.getDate(4).toLocalDate());

                bookPublisherList.add(location);
            }
            ps.close();
            resultSet.close();

            return bookPublisherList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static BookPublisherModel getBookPublisherById(int locationId, DBConnection connection) {
        String sql = "SELECT * FROM livro_editora WHERE pk_livro_editora = ?";

        try {
            BookPublisherModel location = new BookPublisherModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, locationId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                location.setBookPublisherId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setPublisherId(resultSet.getInt(3));
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
