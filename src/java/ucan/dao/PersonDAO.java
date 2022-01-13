package ucan.dao;

import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.PersonModel;
import ucan.conection.DBConnection;

public class PersonDAO {

    public PersonDAO() {

    }

    public void create(PersonModel person, DBConnection connection) {
        String sql = "INSERT INTO pessoa(nome, sobrenome, bi, telefone, email, data_nasc, fk_morada, fk_sexo) values(?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getBi());
            ps.setString(4, person.getPhone());
            ps.setString(5, person.getEmail());
            ps.setTimestamp(6, Timestamp.valueOf(person.getBirthDate()));
            ps.setInt(7, person.getAddressId());
            ps.setInt(8, person.getGenderId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PersonModel person, DBConnection connection) {
        String sql = "UPDATE pessoa SET nome = ?, sobrenome = ?, bi = ?, telefone = ?, email = ?, data_nasc = ?, fk_morada = ?, fk_sexo = ? WHERE pk_pessoa = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, person.getName());
            ps.setString(2, person.getSurname());
            ps.setString(3, person.getSurname());
            ps.setString(4, person.getPhone());
            ps.setString(5, person.getEmail());
            ps.setTimestamp(6, Timestamp.valueOf(person.getBirthDate()));
            ps.setInt(7, person.getAddressId());
            ps.setInt(8, person.getGenderId());
            ps.setInt(9, person.getPersonId());

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
                person.setPhone(resultSet.getString(5));
                person.setBirthDate(resultSet.getTimestamp(6).toLocalDateTime());
                person.setEmail(resultSet.getString(7));
                person.setAddressId(resultSet.getInt(8));
                person.setGenderId(resultSet.getInt(9));
                person.setCreationDate(resultSet.getTimestamp(10).toLocalDateTime());

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
                person.setPhone(resultSet.getString(5));
                person.setBirthDate(resultSet.getTimestamp(6).toLocalDateTime());
                person.setEmail(resultSet.getString(7));
                person.setAddressId(resultSet.getInt(8));
                person.setGenderId(resultSet.getInt(9));
                person.setCreationDate(resultSet.getTimestamp(10).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return person;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
