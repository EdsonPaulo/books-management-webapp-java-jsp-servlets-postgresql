package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.conection.DBConnection;
import ucan.models.PublisherPhoneModel;

public class PublisherPhoneDAO {

    public PublisherPhoneDAO() {

    }

    public void create(PublisherPhoneModel publisherPhone, DBConnection connection) {
        String sql = "INSERT INTO telefone_editora(numero, fk_editora) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, publisherPhone.getPhone());
            ps.setInt(2, publisherPhone.getPublisherId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PublisherPhoneModel publisherPhone, DBConnection connection) {
        String sql = "UPDATE telefone_editora SET numero = ?, fk_editora = ? WHERE pk_telefone_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, publisherPhone.getPhone());
            ps.setInt(2, publisherPhone.getPublisherId());
            ps.setInt(3, publisherPhone.getPublisherPhoneId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int personPhoneId, DBConnection connection) {
        String sql = "DELETE FROM telefone_editora WHERE pk_telefone_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, personPhoneId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PublisherPhoneModel> getAllByPublisherId(int publisherId, DBConnection connection) {
        String sql = "SELECT * FROM telefone_editora WHERE fk_editora = " + publisherId;

        List<PublisherPhoneModel> publisherPhones = new ArrayList<>();
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PublisherPhoneModel publisherPhone = new PublisherPhoneModel();
                publisherPhone.setPublisherPhoneId(resultSet.getInt(1));
                publisherPhone.setPhone(resultSet.getString(2));
                publisherPhone.setPublisherId(resultSet.getInt(3));
                publisherPhone.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                publisherPhones.add(publisherPhone);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisherPhones;
    }
}
