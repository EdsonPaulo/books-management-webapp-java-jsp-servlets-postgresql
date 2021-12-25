package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.ReaderModel;
import ucan.utils.DBConnection;

public class ReaderDAO {

    public ReaderDAO() {

    }

    public static void create(ReaderModel reader, DBConnection connection) {
        String sql = "INSERT INTO leitor(fk_pessoa) values(?)";
        try {
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

    public static void update(ReaderModel reader, DBConnection connection) {
        String sql = "UPDATE leitor SET fk_pessoa = ? WHERE pk_leitor = ?";
        try {
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

    public static void delete(int readerId, DBConnection connection) {
        String sql = "DELETE FROM leitor WHERE pk_leitor = ?";
        try {
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

    public static List<ReaderModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM leitor";

        List<ReaderModel> readerList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                ReaderModel reader = new ReaderModel();
                reader.setReaderId(resultSet.getInt(1));
                reader.setPersonId(resultSet.getInt(2));
                reader.setCreationDate(resultSet.getDate(3).toLocalDate());

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

    public static ReaderModel getBookById(int leitorId, DBConnection connection) {
        String sql = "SELECT * FROM leitor WHERE pk_leitor = ?";

        try {
            ReaderModel reader = new ReaderModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, leitorId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                reader.setReaderId(resultSet.getInt(1));
                reader.setPersonId(resultSet.getInt(2));
                reader.setCreationDate(resultSet.getDate(3).toLocalDate());
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
