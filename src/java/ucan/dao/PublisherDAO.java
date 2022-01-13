package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.PublisherModel;
import ucan.conection.DBConnection;

public class PublisherDAO {

    public PublisherDAO() {

    }

    public void create(PublisherModel publisher, DBConnection connection) {
        String sql = "INSERT INTO editora(nome, telefone, email, fax, fk_morada) values(?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, publisher.getName());
            ps.setString(2, publisher.getPhone());
            ps.setString(3, publisher.getEmail());
            ps.setString(4, publisher.getFax());
            ps.setInt(5, publisher.getAddressId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PublisherModel publisher, DBConnection connection) {
        String sql = "UPDATE editora SET name = ?, telefone = ?, email = ?, fax = ?, fk_morada = ? WHERE pk_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, publisher.getName());
            ps.setString(2, publisher.getPhone());
            ps.setString(3, publisher.getEmail());
            ps.setString(4, publisher.getFax());
            ps.setInt(5, publisher.getAddressId());
            ps.setInt(6, publisher.getPublisherId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int publisherId, DBConnection connection) {
        String sql = "DELETE FROM editora WHERE pk_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, publisherId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PublisherModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM editora";

        List<PublisherModel> publisherList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PublisherModel publisher = new PublisherModel();
                publisher.setPublisherId(resultSet.getInt(1));
                publisher.setName(resultSet.getString(2));
                publisher.setPhone(resultSet.getString(3));
                publisher.setEmail(resultSet.getString(4));
                publisher.setFax(resultSet.getString(5));
                publisher.setAddressId(resultSet.getInt(6));
                publisher.setCreationDate(resultSet.getTimestamp(7).toLocalDateTime());

                publisherList.add(publisher);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisherList;
    }

    public PublisherModel getPublisherById(int publisherId, DBConnection connection) {

        String sql = "SELECT * FROM editora WHERE pk_editora = ?";

        try {
            PublisherModel publisher = new PublisherModel();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, publisherId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                publisher.setPublisherId(resultSet.getInt(1));
                publisher.setName(resultSet.getString(2));
                publisher.setPhone(resultSet.getString(3));
                publisher.setEmail(resultSet.getString(4));
                publisher.setFax(resultSet.getString(5));
                publisher.setAddressId(resultSet.getInt(6));
                publisher.setCreationDate(resultSet.getTimestamp(7).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return publisher;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
