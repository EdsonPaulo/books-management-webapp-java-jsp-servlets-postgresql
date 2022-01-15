
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import ucan.conection.DBConnection;
import ucan.dao.PersonDAO;
import ucan.models.PersonModel;

/**
 *
 * @author edsonpaulo
 */
public class MainTest {

    public static void main(String Args[]) {
        DBConnection connection = null;
        try {
            connection = new DBConnection();
            PersonDAO personDAO = new PersonDAO();
            PersonModel person = new PersonModel();
            person.setName("Edson Paulo");
            person.setSurname("Gregorio");
            person.setBi("005LA3112DD");
            person.setPhone("942682194");

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            person.setBirthDate(LocalDateTime.parse("26-03-2000 12:33", dateFormatter));

            person.setEmail("edsonpaulo24@gmail.com");
            person.setAddressId(1);
            person.setGenderId(1);
            personDAO.create(person, connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }

    }
}
