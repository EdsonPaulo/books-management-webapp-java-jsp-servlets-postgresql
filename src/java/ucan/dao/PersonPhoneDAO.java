package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.conection.DBConnection;
import ucan.models.PersonPhoneModel;

public class PersonPhoneDAO {

    public PersonPhoneDAO() {

    }

    public void create(PersonPhoneModel personPhone, DBConnection connection) {
        String sql = "INSERT INTO telefone_pessoa(numero, fk_pessoa) values(?, ?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, personPhone.getPhone());
            ps.setInt(2, personPhone.getPersonId());

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PersonPhoneModel personPhone, DBConnection connection) {
        String sql = "UPDATE telefone_pessoa SET numero = ?, fk_pessoa = ? WHERE pk_telefone_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setString(1, personPhone.getPhone());
            ps.setInt(2, personPhone.getPersonId());
            ps.setInt(3, personPhone.getPersonPhoneId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int personPhoneId, DBConnection connection) {
        String sql = "DELETE FROM telefone_pessoa WHERE pk_telefone_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, personPhoneId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PersonPhoneModel> getAllByPersonId(int personId, DBConnection connection) {
        String sql = "SELECT * FROM telefone_pessoa WHERE fk_pessoa = " + personId;

        List<PersonPhoneModel> personPhones = new ArrayList<>();
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PersonPhoneModel personPhone = new PersonPhoneModel();
                personPhone.setPersonPhoneId(resultSet.getInt(1));
                personPhone.setPhone(resultSet.getString(2));
                personPhone.setPersonId(resultSet.getInt(3));
                personPhone.setCreationDate(resultSet.getTimestamp(4).toLocalDateTime());

                personPhones.add(personPhone);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personPhones;
    }
}
