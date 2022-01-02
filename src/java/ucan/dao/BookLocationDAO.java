package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookLocationModel;
import ucan.utils.DBConnection;

public class BookLocationDAO {
    private DBConnection connection;

    public BookLocationDAO() {
    }

    public void create(BookLocationModel bookLocation) {
        String sql = "INSERT INTO localizacao_livro(num_corredor, num_armario, num_prateleira) values(?, ?, ?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookLocation.getHallwayNum());
            ps.setInt(2, bookLocation.getCabinetNum());            
            ps.setInt(3, bookLocation.getRackNum());

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

    public void update(BookLocationModel bookLocation) {
        String sql = "UPDATE localizacao_livro SET num_corredor = ?, num_armario = ?, num_prateleira = ? WHERE pk_localizacao_livro = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setInt(1, bookLocation.getHallwayNum());
            ps.setInt(2, bookLocation.getCabinetNum());            
            ps.setInt(3, bookLocation.getRackNum());
            ps.setInt(4, bookLocation.getLocationId());

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

    public void delete(int locationId) {
        String sql = "DELETE FROM localizacao_livro WHERE pk_localizacao_livro = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, locationId);

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

    public List<BookLocationModel> getAll() {
        String sql = "SELECT * FROM localizacao_livro";

        List<BookLocationModel> locationList = new ArrayList<>();

        try {
            connection = new DBConnection();
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

            return locationList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public BookLocationModel getBookLocationById(int locationId) {
        String sql = "SELECT * FROM localizacao_livro WHERE pk_localizacao_livro = ?";

        try {
            BookLocationModel location = new BookLocationModel();
            connection = new DBConnection();
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
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return null;
    }
}
