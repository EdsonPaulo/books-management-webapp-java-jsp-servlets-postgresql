package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.AddressModel;
import ucan.conection.DBConnection;

public class AddressDAO {

    public AddressDAO() {

    }

    public void create(AddressModel address, DBConnection connection) {
        String sql = "INSERT INTO morada(num_casa, rua, bairro, fk_comuna) values(?, ?, ?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, address.getHouseNum());
            ps.setString(2, address.getStreet());
            ps.setString(3, address.getDistrict());
            ps.setInt(4, address.getCommuneId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(AddressModel address, DBConnection connection) {
        String sql = "UPDATE morada SET num_casa = ?, rua = ?, bairro = ?, fk_comuna = ? WHERE pk_morada = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, address.getHouseNum());
            ps.setString(2, address.getStreet());
            ps.setString(3, address.getDistrict());            
            ps.setInt(4, address.getCommuneId());
            ps.setInt(5, address.getAddressId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int addressId, DBConnection connection) {
        String sql = "DELETE FROM morada WHERE pk_morada = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, addressId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<AddressModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM morada";

        List<AddressModel> addressList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                AddressModel address = new AddressModel();
                address.setAddressId(resultSet.getInt(1));
                address.setStreet(resultSet.getString(2));
                address.setHouseNum(resultSet.getInt(3));
                address.setDistrict(resultSet.getString(4));
                address.setCommuneId(resultSet.getInt(5));
                address.setCreationDate(resultSet.getTimestamp(6).toLocalDateTime());

                addressList.add(address);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return addressList;
    }

    public AddressModel getAddressById(int addressId, DBConnection connection) {
        String sql = "SELECT * FROM morada WHERE pk_morada = ?";

        try {
            AddressModel address = new AddressModel();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, addressId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                address.setAddressId(resultSet.getInt(1));
                address.setStreet(resultSet.getString(2));
                address.setHouseNum(resultSet.getInt(3));
                address.setDistrict(resultSet.getString(4));
                address.setCommuneId(resultSet.getInt(5));
                address.setCreationDate(resultSet.getTimestamp(6).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return address;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
