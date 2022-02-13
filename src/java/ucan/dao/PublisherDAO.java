package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import ucan.models.PublisherModel;
import ucan.conection.DBConnection;

public class PublisherDAO {

    public PublisherDAO() {

    }

    public void create(PublisherModel publisher, DBConnection connection) {
        String sql = "INSERT INTO editora(nome, nif, fax, fk_morada) values(?,?,?,?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, publisher.getName());
            ps.setString(2, publisher.getNif());
            ps.setString(3, publisher.getFax());
            ps.setInt(4, publisher.getAddressId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PublisherModel publisher, DBConnection connection) {
        String sql = "UPDATE editora SET name = ?, nif = ?, fax = ?, fk_morada = ? WHERE pk_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, publisher.getName());
            ps.setString(2, publisher.getNif());
            ps.setString(3, publisher.getFax());
            ps.setInt(4, publisher.getAddressId());
            ps.setInt(5, publisher.getPublisherId());

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
                publisher.setNif(resultSet.getString(3));
                publisher.setFax(resultSet.getString(4));
                publisher.setAddressId(resultSet.getInt(5));
                publisher.setCreationDate(resultSet.getTimestamp(6).toLocalDateTime());

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
                publisher.setNif(resultSet.getString(3));
                publisher.setFax(resultSet.getString(4));
                publisher.setAddressId(resultSet.getInt(5));
                publisher.setCreationDate(resultSet.getTimestamp(6).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return publisher;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<String> getPublisherEmails(int publisherId, DBConnection connection) {
        Vector<String> emails = new Vector<>();
        String sql = "SELECT email FROM email_editora WHERE fk_editora = " + publisherId;
        try {
            ResultSet resultSet = connection.getConnection().prepareStatement(sql).executeQuery();

            while (resultSet.next()) {
                emails.add(resultSet.getString(1));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return emails;
    }

    public Vector<String> getPublisherPhones(int publisherId, DBConnection connection) {
        Vector<String> phones = new Vector<>();
        String sql = "SELECT numero FROM telefone_editora WHERE fk_editora = " + publisherId;
        try {
            ResultSet resultSet = connection.getConnection().prepareStatement(sql).executeQuery();

            while (resultSet.next()) {
                phones.add(resultSet.getString(1));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phones;
    }

}
