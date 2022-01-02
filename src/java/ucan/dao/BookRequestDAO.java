package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookRequestModel;
import ucan.utils.DBConnection;

public class BookRequestDAO {
    private DBConnection connection;

    public BookRequestDAO() {
    }

    public void create(BookRequestModel bookRequest) {
        String sql = "INSERT INTO requisicao(fk_livro, fk_leitor, data_requisicao, data_entrega) values(?, ?, ?, ?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookRequest.getBookId());
            ps.setInt(2, bookRequest.getReaderId());
            ps.setTimestamp(3, Timestamp.valueOf(bookRequest.getRequestDate()));
            ps.setTimestamp(4, Timestamp.valueOf(bookRequest.getReturnDate()));

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

    public void update(BookRequestModel bookRequest) {
        String sql = "UPDATE requisicao SET fk_livro = ?, fk_leitor = ?, data_requisicao = ?, data_entrega = ? WHERE pk_requisicao = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, bookRequest.getBookId());
            ps.setInt(2, bookRequest.getReaderId());
            ps.setTimestamp(3, Timestamp.valueOf(bookRequest.getRequestDate()));
            ps.setTimestamp(4, Timestamp.valueOf(bookRequest.getReturnDate()));
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

    public void delete(int requestId) {
        String sql = "DELETE FROM requisicao WHERE pk_requisicao = ?";
        try {
            connection = new DBConnection();
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

    public List<BookRequestModel> getAll() {
        String sql = "SELECT * FROM requisicao";

        List<BookRequestModel> requestList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookRequestModel request = new BookRequestModel();
                request.setRequestId(resultSet.getInt(1));
                request.setBookId(resultSet.getInt(2));
                request.setReaderId(resultSet.getInt(3));
                request.setRequestDate(resultSet.getTimestamp(4).toLocalDateTime());
                request.setReturnDate(resultSet.getTimestamp(5).toLocalDateTime());
                request.setCreationDate(resultSet.getTimestamp(6).toLocalDateTime());

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

    public BookRequestModel getBookRequestById(int requestId) {
        String sql = "SELECT * FROM requisicao WHERE pk_requisicao = ?";

        try {
            BookRequestModel request = new BookRequestModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, requestId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                request.setRequestId(resultSet.getInt(1));
                request.setBookId(resultSet.getInt(2));
                request.setReaderId(resultSet.getInt(3));
                request.setRequestDate(resultSet.getTimestamp(4).toLocalDateTime());
                request.setReturnDate(resultSet.getTimestamp(5).toLocalDateTime());
                request.setCreationDate(resultSet.getTimestamp(6).toLocalDateTime());
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
