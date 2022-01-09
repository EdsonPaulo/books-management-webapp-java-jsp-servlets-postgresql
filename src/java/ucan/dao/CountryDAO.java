package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.CountryModel;
import ucan.conection.DBConnection;

public class CountryDAO {

    private DBConnection connection;

    public CountryDAO() {

    }

    public void create(CountryModel country) {
        String sql = "INSERT INTO pais(nome) values(?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, country.getName());

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

    public void update(CountryModel country) {
        String sql = "UPDATE pais SET nome = ? WHERE pk_pais = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, country.getName());
            ps.setInt(2, country.getCountryId());

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

    public void delete(int countryId) {
        String sql = "DELETE FROM pais WHERE pk_pais = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);

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

    public List<CountryModel> getAll() {
        String sql = "SELECT * FROM pais";

        List<CountryModel> countryList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                CountryModel country = new CountryModel();
                country.setCountryId(resultSet.getInt(1));
                country.setName(resultSet.getString(2));
                country.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());

                countryList.add(country);
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
        return countryList;
    }

    public CountryModel getCountryById(int countryId) {
        String sql = "SELECT * FROM pais WHERE pk_pais = ?";

        try {
            CountryModel country = new CountryModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, countryId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                country.setCountryId(resultSet.getInt(1));
                country.setName(resultSet.getString(2));
                country.setCreationDate(resultSet.getTimestamp(3).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return country;

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
