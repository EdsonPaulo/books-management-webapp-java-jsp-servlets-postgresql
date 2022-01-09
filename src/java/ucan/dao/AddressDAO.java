package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.AddressModel;
import ucan.conection.DBConnection;

public class AddressDAO {

    private DBConnection connection;

    public AddressDAO() {

    }

    public void create(AddressModel address) {
        String sql = "INSERT INTO morada(num_casa, rua, fk_bairro) values(?, ?, ?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, address.getHouseNum());
            ps.setString(2, address.getStreet());
            ps.setInt(3, address.getDistrictId());

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

    public void update(AddressModel address) {
        String sql = "UPDATE morada SET num_casa = ?, rua = ?, fk_bairro = ? WHERE pk_morada = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, address.getHouseNum());
            ps.setString(2, address.getStreet());
            ps.setInt(3, address.getDistrictId());
            ps.setInt(4, address.getAddressId());

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

    public void delete(int addressId) {
        String sql = "DELETE FROM morada WHERE pk_morada = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, addressId);

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

    public List<AddressModel> getAll() {
        String sql = "SELECT * FROM morada";

        List<AddressModel> addressList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                AddressModel address = new AddressModel();
                address.setAddressId(resultSet.getInt(1));
                address.setStreet(resultSet.getString(2));
                address.setHouseNum(resultSet.getInt(3));
                address.setDistrictId(resultSet.getInt(4));
                address.setCreationDate(resultSet.getTimestamp(5).toLocalDateTime());

                addressList.add(address);
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
        return addressList;
    }

    public AddressModel getaddressById(int addressId) {
        String sql = "SELECT * FROM morada WHERE pk_morada = ?";

        try {
            AddressModel address = new AddressModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, addressId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                address.setAddressId(resultSet.getInt(1));
                address.setStreet(resultSet.getString(2));
                address.setHouseNum(resultSet.getInt(3));
                address.setDistrictId(resultSet.getInt(4));
                address.setCreationDate(resultSet.getTimestamp(5).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return address;

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
