package ucan.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookRequestModel;
import ucan.utils.DBConnection;

public class BookRequestDAO {

    public BookRequestDAO() {
    }

    public static void create(BookRequestModel bookRequest, DBConnection connection) {
        String sql = "INSERT INTO requisicao(fk_livro, fk_leitor, data_requisicao, data_entrega) values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookRequest.getBookId());
            ps.setInt(2, bookRequest.getReaderId());
            ps.setDate(3, Date.valueOf(bookRequest.getRequestDate()));
            ps.setDate(4, Date.valueOf(bookRequest.getReturnDate()));

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

    public static void update(BookRequestModel bookRequest, DBConnection connection) {
        String sql = "UPDATE requisicao SET fk_livro = ?, fk_leitor = ?, data_requisicao = ?, data_entrega = ? WHERE pk_requisicao = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, bookRequest.getBookId());
            ps.setInt(2, bookRequest.getReaderId());
            ps.setDate(3, Date.valueOf(bookRequest.getRequestDate()));
            ps.setDate(4, Date.valueOf(bookRequest.getReturnDate()));
            ps.setInt(5, bookRequest.getRequestId());

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

    public static void delete(int requestId, DBConnection connection) {
        String sql = "DELETE FROM requisicao WHERE pk_requisicao = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, requestId);

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

    public static List<BookRequestModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM requisicao";

        List<BookRequestModel> requestList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookRequestModel request = new BookRequestModel();
                request.setRequestId(resultSet.getInt(1));
                request.setBookId(resultSet.getInt(2));
                request.setReaderId(resultSet.getInt(3));
                request.setRequestDate(resultSet.getDate(4).toLocalDate());
                request.setReturnDate(resultSet.getDate(5).toLocalDate());
                request.setCreationDate(resultSet.getDate(6).toLocalDate());

                requestList.add(request);
            }
            ps.close();
            resultSet.close();

            return requestList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public static BookRequestModel getBookRequestById(int requestId, DBConnection connection) {
        String sql = "SELECT * FROM requisicao WHERE pk_requisicao = ?";

        try {
            BookRequestModel request = new BookRequestModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, requestId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                request.setRequestId(resultSet.getInt(1));
                request.setBookId(resultSet.getInt(2));
                request.setReaderId(resultSet.getInt(3));
                request.setRequestDate(resultSet.getDate(4).toLocalDate());
                request.setReturnDate(resultSet.getDate(5).toLocalDate());
                request.setCreationDate(resultSet.getDate(6).toLocalDate());
            }

            ps.close();
            resultSet.close();
            return request;

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
