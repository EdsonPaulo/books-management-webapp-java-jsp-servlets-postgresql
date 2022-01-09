package ucan.controllers;

import ucan.utils.Helpers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucan.dao.AddressDAO;
import ucan.dao.PersonDAO;
import ucan.models.AddressModel;
import ucan.models.PersonModel;

/**
 *
 * @author edsonpaulo
 */
@WebServlet(name = "PersonServlet", urlPatterns = {"/pessoa"})
public class PersonServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        if (request.getMethod() == "POST") {
            try {
                PersonModel person = new PersonModel();
                PersonDAO personDao = new PersonDAO();

                person.setName(request.getParameter("name"));
                person.setSurname(request.getParameter("surname"));
                person.setBi(request.getParameter("bi"));
                person.setPhone(request.getParameter("phone"));
                person.setEmail(request.getParameter("email"));
                person.setBirthDate(Helpers.stringToDateTime(request.getParameter("birthDate"), true));
                person.setGenderId(Integer.parseInt(request.getParameter("gender")));

                System.out.println(person.toString());

                /**
                 * INSERT ADDRESS BEFORE CREATE PERSON
                 */
                AddressModel address = new AddressModel();
                AddressDAO addressDao = new AddressDAO();

                address.setStreet(request.getParameter("street"));
                address.setHouseNum(Integer.parseInt(request.getParameter("houseNum")));
                //address.setDistrictId(Integer.parseInt(request.getParameter("district")));
                address.setDistrictId(0);

                System.out.println(address);

                addressDao.create(address);

                person.setAddressId(Helpers.getIdOfLastRow("morada"));

                System.out.println(person);

                personDao.create(person);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        response.sendRedirect(request.getContextPath() + "/index.jsp");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
