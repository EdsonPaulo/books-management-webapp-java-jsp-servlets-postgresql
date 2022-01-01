package ucan.dao;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.PersonModel;
import ucan.utils.DBConnection;

public class PersonDAO {
    private DBConnection connection;

    public PersonDAO() {

    }

    public void create(PersonModel person) {
        String sql = "INSERT INTO pessoa(nome, sobrenome, telefone, email, data_nasc, fk_morada, fk_sexo) values(?,?,?,?,?,?,?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getPhone());
            ps.setString(4, person.getEmail());
            ps.setTimestamp(5, Timestamp.valueOf(person.getBirthDate()));
            ps.setInt(6, person.getAddressId());
            ps.setInt(7, person.getGenderId());

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

    public void update(PersonModel person) {
        String sql = "UPDATE pessoa SET name = ?, surname = ?, telefone = ?, email = ?, data_nasc = ?, fk_morada = ?, fk_sexo = ? WHERE pk_pessoa = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getPhone());
            ps.setString(4, person.getEmail());
            ps.setTimestamp(5, Timestamp.valueOf(person.getBirthDate()));
            ps.setInt(6, person.getAddressId());
            ps.setInt(7, person.getGenderId());
            ps.setInt(8, person.getPersonId());

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

    public void delete(int personId) {
        String sql = "DELETE FROM pessoa WHERE pk_pessoa = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, personId);

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

    public List<PersonModel> getAll() {
        String sql = "SELECT * FROM pessoa";

        List<PersonModel> personList = new ArrayList<>();

        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                PersonModel person = new PersonModel();
                person.setPersonId(resultSet.getInt(1));
                person.setName(resultSet.getString(2));
                person.setSurname(resultSet.getString(3));
                person.setPhone(resultSet.getString(4));
                person.setBirthDate(resultSet.getTimestamp(5).toLocalDateTime());
                person.setEmail(resultSet.getString(6));
                person.setAddressId(resultSet.getInt(7));
                person.setGenderId(resultSet.getInt(8));
                person.setCreationDate(resultSet.getTimestamp(9).toLocalDateTime());

                personList.add(person);
            }
            ps.close();
            resultSet.close();

            return personList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public PersonModel getPersonById(int personId) {

        String sql = "SELECT * FROM pessoa WHERE pk_pessoa = ?";

        try {
            PersonModel person = new PersonModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, personId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                 person.setPersonId(resultSet.getInt(1));
                person.setName(resultSet.getString(2));
                person.setSurname(resultSet.getString(3));
                person.setPhone(resultSet.getString(4));
                person.setBirthDate(resultSet.getTimestamp(5).toLocalDateTime());
                person.setEmail(resultSet.getString(6));
                person.setAddressId(resultSet.getInt(7));
                person.setGenderId(resultSet.getInt(8));
                person.setCreationDate(resultSet.getTimestamp(9).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return person;

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
