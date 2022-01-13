package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.ReaderModel;
import ucan.conection.DBConnection;

public class ReaderDAO {

    public ReaderDAO() {

    }

    public void create(ReaderModel reader, DBConnection connection) {
        String sql = "INSERT INTO leitor(fk_pessoa) values(?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, reader.getPersonId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(ReaderModel reader, DBConnection connection) {
        String sql = "UPDATE leitor SET fk_pessoa = ? WHERE pk_leitor = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, reader.getPersonId());
            ps.setInt(2, reader.getReaderId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int readerId, DBConnection connection) {
        String sql = "DELETE FROM leitor WHERE pk_leitor = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, readerId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ReaderModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM leitor";

        List<ReaderModel> readerList = new ArrayList<>();

        try {
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

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readerList;
    }

    public ReaderModel getBookById(int readerId, DBConnection connection) {
        String sql = "SELECT * FROM leitor WHERE pk_leitor = ?";

        try {
            ReaderModel reader = new ReaderModel();
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
        }
        return null;
    }
}
