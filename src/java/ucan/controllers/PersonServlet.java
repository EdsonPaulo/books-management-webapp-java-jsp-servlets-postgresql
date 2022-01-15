package ucan.controllers;

import com.google.gson.Gson;
import ucan.utils.Helpers;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucan.conection.DBConnection;
import ucan.dao.AddressDAO;
import ucan.dao.CommuneDAO;
import ucan.dao.CountryDAO;
import ucan.dao.MunicipalityDAO;
import ucan.dao.PersonDAO;
import ucan.dao.ProvinceDAO;
import ucan.models.AddressModel;
import ucan.models.CommuneModel;
import ucan.models.CountryModel;
import ucan.models.MunicipalityModel;
import ucan.models.PersonModel;
import ucan.models.ProvinceModel;

/**
 *
 * @author edsonpaulo
 */
@WebServlet(name = "PersonServlet", urlPatterns = {"/pessoa"})
public class PersonServlet extends HttpServlet {

    private DBConnection connection;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try {
            connection = new DBConnection();

            if (request.getParameter("operation") != null) {
                CountryDAO countryDao = new CountryDAO();
                ProvinceDAO provinceDao = new ProvinceDAO();
                MunicipalityDAO muniDao = new MunicipalityDAO();
                CommuneDAO communeDao = new CommuneDAO();

                Gson json = new Gson();
                PrintWriter writer = response.getWriter();
                response.setContentType("text/html");
                String op = request.getParameter("operation");

                if (op.equals("country")) {
                    List<CountryModel> clist = countryDao.getAll(connection);
                    response.getWriter().write(json.toJson(clist));
                } else {
                    int id = Integer.parseInt(request.getParameter("id"));

                    if (op.equals("province")) {
                        List<ProvinceModel> provinceList = provinceDao.getProvincesByCountryId(id, connection);
                        writer.write(json.toJson(provinceList));

                    } else if (op.equals("municipality")) {
                        List<MunicipalityModel> muniList = muniDao.getMunicipalitiesByProvinceId(id, connection);
                        writer.write(json.toJson(muniList));

                    } else if (op.equals("commune")) {
                        List<CommuneModel> communeList = communeDao.getCommunesByMunicipalityId(id, connection);
                        writer.write(json.toJson(communeList));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);

        try {
            connection = new DBConnection();
            PersonModel person = new PersonModel();
            PersonDAO personDao = new PersonDAO();

            person.setName(request.getParameter("name"));
            person.setSurname(request.getParameter("surname"));
            person.setBi(request.getParameter("bi"));
            person.setPhone(request.getParameter("phone"));
            person.setEmail(request.getParameter("email"));
            person.setBirthDate(Helpers.stringToDateTime(request.getParameter("birthDate"), true));
            person.setGenderId(Integer.parseInt(request.getParameter("gender")));

            /**
             * INSERT ADDRESS BEFORE CREATE PERSON
             */
            AddressModel address = new AddressModel();
            AddressDAO addressDao = new AddressDAO();

            address.setStreet(request.getParameter("street"));
            address.setHouseNum(Integer.parseInt(request.getParameter("houseNum")));
            address.setDistrict(request.getParameter("district"));
            address.setCommuneId(Integer.parseInt(request.getParameter("commune")));

            addressDao.create(address, connection);

            person.setAddressId(Helpers.getIdOfLastRow("morada", connection));

            personDao.create(person, connection);
            
            response.sendRedirect(request.getContextPath() + "/listar-pessoas.jsp");

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
