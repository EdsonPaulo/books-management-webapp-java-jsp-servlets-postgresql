
import ucan.dao.GenderDAO;
import ucan.models.GenderModel;
import ucan.utils.DBConnection;


/**
 *
 * @author edsonpaulo
 */
public class MainTest {
    
    public static void main(String Args[]) {
        GenderDAO genderDAO = new GenderDAO();
        System.out.println(genderDAO.getGenderById(2));
    }
    
}
