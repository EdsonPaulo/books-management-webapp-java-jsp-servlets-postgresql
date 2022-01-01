package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.ReaderModel;
import ucan.utils.DBConnection;

public class ReaderDAO {
    private DBConnection connection;

    public ReaderDAO() {

    }

    public void create(ReaderModel reader) {
        String sql = "INSERT INTO leitor(fk_pessoa) values(?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, reader.getPersonId());

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

    public void update(ReaderModel reader) {
        String sql = "UPDATE leitor SET fk_pessoa = ? WHERE pk_leitor = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, reader.getPersonId());
            ps.setInt(2, reader.getReaderId());

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

    public void delete(int readerId) {
        String sql = "DELETE FROM leitor WHERE pk_leitor = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, readerId);
            
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

    public List<ReaderModel> getAll() {
        String sql = "SELECT * FROM leitor";

        List<ReaderModel> readerList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ReaderModel reader = new ReaderModel();
                reader.setReaderId(resultSet.getInt(1));
                reader.setPersonId(resultSet.getInt(2));
                reader.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

                readerList.add(reader);
            }
            ps.close();
            resultSet.close();

            return readerList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public ReaderModel getBookById(int readerId) {
        String sql = "SELECT * FROM leitor WHERE pk_leitor = ?";

        try {
            ReaderModel reader = new ReaderModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, readerId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                reader.setReaderId(resultSet.getInt(1));
                reader.setPersonId(resultSet.getInt(2));
                reader.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return reader;

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
