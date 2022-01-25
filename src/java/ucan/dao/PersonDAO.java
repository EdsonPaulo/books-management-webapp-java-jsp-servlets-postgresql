package ucan.dao;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import ucan.models.PersonModel;
import ucan.conection.DBConnection;

public class PersonDAO {

    public PersonDAO() {

    }

    public void create(PersonModel person, DBConnection connection) {
        String sql = "INSERT INTO pessoa(nome, sobrenome, bi, data_nasc, fk_morada, fk_sexo) values(?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getBi());
            ps.setTimestamp(4, Timestamp.valueOf(person.getBirthDate()));
            ps.setInt(5, person.getAddressId());
            ps.setInt(6, person.getGenderId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PersonModel person, DBConnection connection) {
        String sql = "UPDATE pessoa SET nome = ?, sobrenome = ?, bi = ?, data_nasc = ?, fk_morada = ?, fk_sexo = ? WHERE pk_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getSurname());
            ps.setTimestamp(4, Timestamp.valueOf(person.getBirthDate()));
            ps.setInt(5, person.getAddressId());
            ps.setInt(6, person.getGenderId());
            ps.setInt(7, person.getPersonId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int personId, DBConnection connection) {
        String sql = "DELETE FROM pessoa WHERE pk_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, personId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<PersonModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM pessoa";

        List<PersonModel> personList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PersonModel person = new PersonModel();
                person.setPersonId(resultSet.getInt(1));
                person.setName(resultSet.getString(2));
                person.setSurname(resultSet.getString(3));
                person.setBi(resultSet.getString(4));
                person.setBirthDate(resultSet.getTimestamp(5).toLocalDateTime());
                person.setAddressId(resultSet.getInt(6));
                person.setGenderId(resultSet.getInt(7));
                person.setCreationDate(resultSet.getTimestamp(8).toLocalDateTime());

                personList.add(person);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personList;
    }

    public PersonModel getPersonById(int personId, DBConnection connection) {

        String sql = "SELECT * FROM pessoa WHERE pk_pessoa = ?";

        try {
            PersonModel person = new PersonModel();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, personId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                person.setPersonId(resultSet.getInt(1));
                person.setName(resultSet.getString(2));
                person.setSurname(resultSet.getString(3));
                person.setBi(resultSet.getString(4));
                person.setBirthDate(resultSet.getTimestamp(5).toLocalDateTime());
                person.setAddressId(resultSet.getInt(6));
                person.setGenderId(resultSet.getInt(7));
                person.setCreationDate(resultSet.getTimestamp(8).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return person;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Vector<String> getPersonEmails(int personId, DBConnection connection) {
        Vector<String> emails = new Vector<>();
        String sql = "SELECT email FROM email_pessoa WHERE pk_pessoa = " + personId;
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

    public Vector<String> getPersonPhones(int personId, DBConnection connection) {
        Vector<String> phones = new Vector<>();
        String sql = "SELECT numero FROM telefone_pessoa WHERE pk_pessoa = " + personId;
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
