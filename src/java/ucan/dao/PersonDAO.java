package ucan.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.PersonModel;
import ucan.utils.DBConnection;

public class PersonDAO {

    public PersonDAO() {

    }

    public static void create(PersonModel person, DBConnection connection) {
        String sql = "INSERT INTO pessoa(nome, sobrenome, telefone, email, data_nasc, fk_morada, fk_sexo) values(?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getPhone());
            ps.setString(4, person.getEmail());
            ps.setDate(5, Date.valueOf(person.getBirthDate()));
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

    public static void update(PersonModel person, DBConnection connection) {
        String sql = "UPDATE pessoa SET name = ?, surname = ?, telefone = ?, email = ?, data_nasc = ?, fk_morada = ?, fk_sexo = ? WHERE pk_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getPhone());
            ps.setString(4, person.getEmail());
            ps.setDate(5, Date.valueOf(person.getBirthDate()));
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

    public static void delete(int personId, DBConnection connection) {
        String sql = "DELETE FROM pessoa WHERE pk_pessoa = ?";
        try {
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

    public static List<PersonModel> getAll(DBConnection connection) {
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
                person.setPhone(resultSet.getString(4));
                person.setBirthDate(resultSet.getDate(5).toLocalDate());
                person.setEmail(resultSet.getString(6));
                person.setAddressId(resultSet.getInt(7));
                person.setGenderId(resultSet.getInt(8));
                person.setCreationDate(resultSet.getDate(9).toLocalDate());

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

    public static PersonModel getPersonById(int personId, DBConnection connection) {

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
                person.setPhone(resultSet.getString(4));
                person.setBirthDate(resultSet.getDate(5).toLocalDate());
                person.setEmail(resultSet.getString(6));
                person.setAddressId(resultSet.getInt(7));
                person.setGenderId(resultSet.getInt(8));
                person.setCreationDate(resultSet.getDate(9).toLocalDate());
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
