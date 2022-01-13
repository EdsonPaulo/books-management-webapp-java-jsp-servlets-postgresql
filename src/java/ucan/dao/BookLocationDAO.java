package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookLocationModel;
import ucan.conection.DBConnection;

public class BookLocationDAO {

    public BookLocationDAO() {
    }

    public void create(BookLocationModel bookLocation, DBConnection connection) {
        String sql = "INSERT INTO localizacao_livro(num_corredor, num_armario, num_prateleira) values(?, ?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookLocation.getHallwayNum());
            ps.setInt(2, bookLocation.getCabinetNum());
            ps.setInt(3, bookLocation.getRackNum());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(BookLocationModel bookLocation, DBConnection connection) {
        String sql = "UPDATE localizacao_livro SET num_corredor = ?, num_armario = ?, num_prateleira = ? WHERE pk_localizacao_livro = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, bookLocation.getHallwayNum());
            ps.setInt(2, bookLocation.getCabinetNum());
            ps.setInt(3, bookLocation.getRackNum());
            ps.setInt(4, bookLocation.getLocationId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int locationId, DBConnection connection) {
        String sql = "DELETE FROM localizacao_livro WHERE pk_localizacao_livro = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, locationId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BookLocationModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM localizacao_livro";

        List<BookLocationModel> locationList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookLocationModel location = new BookLocationModel();
                location.setLocationId(resultSet.getInt(1));
                location.setHallwayNum(resultSet.getInt(2));
                location.setCabinetNum(resultSet.getInt(3));
                location.setRackNum(resultSet.getInt(4));
                location.setCreationDate(resultSet.getTimestamp(5).toLocalDateTime());

                locationList.add(location);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return locationList;
    }

    public BookLocationModel getBookLocationById(int locationId, DBConnection connection) {
        String sql = "SELECT * FROM localizacao_livro WHERE pk_localizacao_livro = ?";

        try {
            BookLocationModel location = new BookLocationModel();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, locationId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                location.setLocationId(resultSet.getInt(1));
                location.setHallwayNum(resultSet.getInt(2));
                location.setCabinetNum(resultSet.getInt(3));
                location.setRackNum(resultSet.getInt(4));
                location.setCreationDate(resultSet.getTimestamp(5).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return location;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
