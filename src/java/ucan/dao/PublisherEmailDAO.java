package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.conection.DBConnection;
import ucan.models.PublisherEmailModel;

public class PublisherEmailDAO {

    public PublisherEmailDAO() {

    }

    public void create(PublisherEmailModel publisherEmail, DBConnection connection) {
        String sql = "INSERT INTO email_editora(email, fk_editora) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, publisherEmail.getEmail());
            ps.setInt(2, publisherEmail.getPublisherId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PublisherEmailModel publisherEmail, DBConnection connection) {
        String sql = "UPDATE email_editora SET email = ?, fk_editora = ? WHERE pk_email_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, publisherEmail.getEmail());
            ps.setInt(2, publisherEmail.getPublisherId());
            ps.setInt(3, publisherEmail.getPublisherEmailId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int publisherEmailId, DBConnection connection) {
        String sql = "DELETE FROM email_editora WHERE pk_email_editora = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, publisherEmailId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PublisherEmailModel> getAllByPublisherId(int publisherId, DBConnection connection) {
        String sql = "SELECT * FROM email_editora WHERE fk_editora = " + publisherId;

        List<PublisherEmailModel> publisherEmails = new ArrayList<>();
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PublisherEmailModel publisherEmail = new PublisherEmailModel();
                publisherEmail.setPublisherEmailId(resultSet.getInt(1));
                publisherEmail.setEmail(resultSet.getString(2));
                publisherEmail.setPublisherId(resultSet.getInt(3));
                publisherEmail.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                publisherEmails.add(publisherEmail);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return publisherEmails;
    }
}
