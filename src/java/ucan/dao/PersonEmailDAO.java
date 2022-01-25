package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.conection.DBConnection;
import ucan.models.PersonEmailModel;

public class PersonEmailDAO {

    public PersonEmailDAO() {

    }

    public void create(PersonEmailModel personEmail, DBConnection connection) {
        String sql = "INSERT INTO email_pessoa(email, fk_pessoa) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, personEmail.getEmail());
            ps.setInt(2, personEmail.getPersonId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PersonEmailModel personEmail, DBConnection connection) {
        String sql = "UPDATE email_pessoa SET email = ?, fk_pessoa = ? WHERE pk_email_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, personEmail.getEmail());
            ps.setInt(2, personEmail.getPersonId());
            ps.setInt(3, personEmail.getPersonEmailId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int personEmailId, DBConnection connection) {
        String sql = "DELETE FROM email_pessoa WHERE pk_email_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, personEmailId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PersonEmailModel> getAllByPersonId(int personId, DBConnection connection) {
        String sql = "SELECT * FROM email_pessoa WHERE fk_pessoa = " + personId;

        List<PersonEmailModel> personEmails = new ArrayList<>();
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PersonEmailModel personEmail = new PersonEmailModel();
                personEmail.setPersonEmailId(resultSet.getInt(1));
                personEmail.setEmail(resultSet.getString(2));
                personEmail.setPersonId(resultSet.getInt(3));
                personEmail.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                personEmails.add(personEmail);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personEmails;
    }
}
