package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookTagModel;
import ucan.utils.DBConnection;

public class BookTagDAO {

    public BookTagDAO() {
    }

    public static void create(BookTagModel bookTag, DBConnection connection) {
        String sql = "INSERT INTO livro_descritores(fk_livro, fk_descritores) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookTag.getBookId());
            ps.setInt(2, bookTag.getTagId());

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

    public static void update(BookTagModel bookTag, DBConnection connection) {
        String sql = "UPDATE livro_descritores SET fk_livro = ?, fk_descritores = ? WHERE pk_livro_descritores = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, bookTag.getBookId());
            ps.setInt(2, bookTag.getTagId());
            ps.setInt(3, bookTag.getBookTagId());

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

    public static void delete(int bookTagId, DBConnection connection) {
        String sql = "DELETE FROM livro_descritores WHERE pk_livro_descritores = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookTagId);

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

    public static List<BookTagModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM livro_descritores";

        List<BookTagModel> bookTagList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookTagModel location = new BookTagModel();
                location.setBookTagId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setTagId(resultSet.getInt(3));
                location.setCreationDate(resultSet.getDate(4).toLocalDate());

                bookTagList.add(location);
            }
            ps.close();
            resultSet.close();

            return bookTagList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static BookTagModel getBookTagById(int locationId, DBConnection connection) {
        String sql = "SELECT * FROM livro_descritores WHERE pk_livro_descritores = ?";

        try {
            BookTagModel location = new BookTagModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, locationId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                location.setBookTagId(resultSet.getInt(1));
                location.setBookId(resultSet.getInt(2));
                location.setTagId(resultSet.getInt(3));
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
