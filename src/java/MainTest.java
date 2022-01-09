
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import ucan.dao.PersonDAO;
import ucan.models.PersonModel;

/**
 *
 * @author edsonpaulo
 */
public class MainTest {

    public static void main(String Args[]) {

        try {
            PersonDAO personDAO = new PersonDAO();
            PersonModel person = new PersonModel();
            person.setName("Edson Paulo");
            person.setSurname("Gregorio");
            person.setBi("005LA3112DD");
            person.setPhone("942682194");
            
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
            person.setBirthDate(LocalDateTime.parse("26-03-2000 12:33", dateFormatter));
            
            person.setEmail("edsonpaulo24@gmail.com");
            person.setAddressId(0);
            person.setGenderId(3);
            personDAO.create(person);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
