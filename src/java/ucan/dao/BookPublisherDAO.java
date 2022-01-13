package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookPublisherModel;
import ucan.conection.DBConnection;

public class BookPublisherDAO {

    public BookPublisherDAO() {
    }

    public void create(BookPublisherModel bookPublisher, DBConnection connection) {
        String sql = "INSERT INTO livro_editora(fk_livro, fk_editora) values(?, ?)";
        try {
            connection = new DBConnection();
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

    public void update(BookPublisherModel bookPublisher, DBConnection connection) {
        String sql = "UPDATE livro_editora SET fk_livro = ?, fk_editora = ? WHERE pk_livro_editora = ?";
        try {
            connection = new DBConnection();
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

    public void delete(int bookPublisherId, DBConnection connection) {
        String sql = "DELETE FROM livro_editora WHERE pk_livro_editora = ?";
        try {
            connection = new DBConnection();
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

    public List<BookPublisherModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM livro_editora";

        List<BookPublisherModel> bookPublisherList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookPublisherModel location = new BookPublisherModel();
                location.setBookPublisherId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setPublisherId(resultSet.getInt(3));
                location.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                bookPublisherList.add(location);
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
        return bookPublisherList;
    }

    public BookPublisherModel getBookPublisherById(int locationId, DBConnection connection) {
        String sql = "SELECT * FROM livro_editora WHERE pk_livro_editora = ?";

        try {
            BookPublisherModel location = new BookPublisherModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, locationId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                location.setBookPublisherId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setPublisherId(resultSet.getInt(3));
                location.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());
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
