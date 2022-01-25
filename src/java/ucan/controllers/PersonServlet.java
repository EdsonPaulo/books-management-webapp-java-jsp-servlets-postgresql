package ucan.controllers;

import com.google.gson.Gson;
import ucan.utils.Helpers;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucan.conection.DBConnection;
import ucan.dao.AddressDAO;
import ucan.dao.AuthorDAO;
import ucan.dao.CommuneDAO;
import ucan.dao.CountryDAO;
import ucan.dao.MunicipalityDAO;
import ucan.dao.PersonDAO;
import ucan.dao.PersonEmailDAO;
import ucan.dao.PersonPhoneDAO;
import ucan.dao.ProvinceDAO;
import ucan.dao.ReaderDAO;
import ucan.models.AddressModel;
import ucan.models.AuthorModel;
import ucan.models.CommuneModel;
import ucan.models.CountryModel;
import ucan.models.MunicipalityModel;
import ucan.models.PersonEmailModel;
import ucan.models.PersonModel;
import ucan.models.PersonPhoneModel;
import ucan.models.ProvinceModel;
import ucan.models.ReaderModel;

/**
 *
 * @author edsonpaulo
 */
@WebServlet(name = "PersonServlet", urlPatterns = {"/person-servlet"})
public class PersonServlet extends HttpServlet {

    private DBConnection connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

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

                    switch (op) {
                        case "province":
                            List<ProvinceModel> provinceList = provinceDao.getProvincesByCountryId(id, connection);
                            writer.write(json.toJson(provinceList));
                            break;
                            
                        case "municipality":
                            List<MunicipalityModel> muniList = muniDao.getMunicipalitiesByProvinceId(id, connection);
                            writer.write(json.toJson(muniList));
                            break;
                            
                        case "commune":
                            List<CommuneModel> communeList = communeDao.getCommunesByMunicipalityId(id, connection);
                            writer.write(json.toJson(communeList));
                            break;
                            
                        default:
                            break;
                    }
                }
            }

            if (request.getParameter("action") != null) {
                PersonDAO personDao = new PersonDAO();
                int id = Integer.parseInt(request.getParameter("id"));
                String action = request.getParameter("action");

                switch (action) {
                    case "delete":
                        personDao.delete(id, connection);
                        response.sendRedirect(request.getContextPath() + "/person/list.jsp");
                        break;
                        
                    default:
                        break;
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }
    
    private void processUpdateRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     try {
            connection = new DBConnection();
            PersonModel person = new PersonModel();
            PersonDAO personDao = new PersonDAO();

            person.setName(request.getParameter("name"));
            person.setSurname(request.getParameter("surname"));
            person.setBi(request.getParameter("bi"));
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
            
            if("POST".equals(request.getMethod())) { 
                addressDao.create(address, connection);
                person.setAddressId(Helpers.getIdOfLastRow("morada", connection));
                personDao.create(person, connection);
                
                int personId = Helpers.getIdOfLastRow("pessoa", connection);
                
                /**
                * INSERT EMAIL AFTER CREATE PERSON
                */
                PersonPhoneDAO personPhoneDao = new PersonPhoneDAO();
                PersonPhoneModel personPhone = new PersonPhoneModel();
                personPhone.setPersonId(personId);
                personPhone.setPhone(request.getParameter("phone1"));
                personPhoneDao.create(personPhone, connection);
                
                if(request.getParameter("phone2") != null){
                    personPhone.setPhone(request.getParameter("phone2"));
                    personPhoneDao.create(personPhone, connection);
                }
                
                /**
                * INSERT PHONE BEFORE CREATE PERSON
                */
                PersonEmailDAO personEmailDao = new PersonEmailDAO();
                PersonEmailModel personEmail = new PersonEmailModel();
                personEmail.setPersonId(personId);
                personEmail.setEmail(request.getParameter("email1"));
                personPhoneDao.create(personPhone, connection);
                
                if(request.getParameter("email2") != null){
                    personEmail.setEmail(request.getParameter("email2"));
                    personEmailDao.create(personEmail, connection);
                }
                
                /**
                 * SAVE PERSON AS AUTHOR OR READER
                 */
                if("AUTHOR".equals(request.getParameter("personType"))) {
                    AuthorDAO authorDao = new AuthorDAO();
                    AuthorModel author = new AuthorModel();
                    author.setPersonId(personId);
                    authorDao.create(author, connection);
                } else {
                    ReaderDAO readerDao = new ReaderDAO();
                    ReaderModel reader = new ReaderModel();
                    reader.setPersonId(personId);
                    readerDao.create(reader, connection);
                }
            }
            else
                if("PUT".equals(request.getMethod())) { 
                    addressDao.update(address, connection);
                    personDao.update(person, connection);
                }

           response.sendRedirect(request.getContextPath() + "/person/list.jsp");

        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processUpdateRequest(request, response);
    }
    
    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processUpdateRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
