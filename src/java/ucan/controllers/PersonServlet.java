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
           System.out.println("action 1 ID: " + request.getParameter("action"));           
           System.out.println("PHONE 1 ID: " + request.getParameter("phone1Id"));

            System.out.println("PHONE 2 ID: " + request.getParameter("phone2Id"));
                    
                    System.out.println("EMAIL 1 ID: " + request.getParameter("email1Id"));
                    System.out.println("EMAIL 2 ID: " + request.getParameter("email2Id"));
                    
                    System.out.println("PERSON ID: " + request.getParameter("personId"));
                    System.out.println("ADDRESS ID: " + request.getParameter("addressId"));

                    
                    
            connection = new DBConnection();
            PersonModel person = new PersonModel();
            PersonDAO personDao = new PersonDAO();

            person.setName(request.getParameter("name"));
            person.setSurname(request.getParameter("surname"));
            person.setBi(request.getParameter("bi"));
            person.setBirthDate(Helpers.stringToDateTime(request.getParameter("birthDate"), true));
            person.setGenderId(Integer.parseInt(request.getParameter("gender")));

            /**
             * FILL ADDRESS
             */
            AddressModel address = new AddressModel();
            AddressDAO addressDao = new AddressDAO();

            address.setStreet(request.getParameter("street"));
            address.setHouseNum(Integer.parseInt(request.getParameter("houseNum")));
            address.setDistrict(request.getParameter("district"));
            address.setCommuneId(Integer.parseInt(request.getParameter("commune")));
            
            /**
             * FILL PHONE
             */
            boolean hasPhone2 = !request.getParameter("phone2").trim().isEmpty();
            PersonPhoneDAO personPhoneDao = new PersonPhoneDAO();
            PersonPhoneModel personPhone1 = new PersonPhoneModel();
            PersonPhoneModel personPhone2 = new PersonPhoneModel();

            personPhone1.setPhone(request.getParameter("phone1"));
            if (hasPhone2) {
                personPhone2.setPhone(request.getParameter("phone2"));
            }
            
            /**
             * FILL EMAIL
             */
            boolean hasEmail2 = !request.getParameter("email2").trim().isEmpty();
            PersonEmailDAO personEmailDao = new PersonEmailDAO();
            PersonEmailModel personEmail1 = new PersonEmailModel();
            PersonEmailModel personEmail2 = new PersonEmailModel();
            
            personEmail1.setEmail(request.getParameter("email1"));
            if (hasEmail2) {
                personEmail2.setEmail(request.getParameter("email2"));
            }
            
            if (request.getParameter("action") == null || request.getParameter("action").isEmpty()) { 
                // CREATE ADDRESS
                addressDao.create(address, connection);
                
                // CREATE PERSON WITH THE ADDRESS ADDED ABOVE
                person.setAddressId(Helpers.getIdOfLastRow("morada", connection));
                personDao.create(person, connection);
                
                int personId = Helpers.getIdOfLastRow("pessoa", connection);
                
                // INSERT PHONE AFTER CREATE PERSON
                personPhone1.setPersonId(personId);
                personPhoneDao.create(personPhone1, connection);
                if(hasPhone2){
                    personPhone2.setPersonId(personId);
                    personPhoneDao.create(personPhone2, connection);
                }
                
                // INSERT EMAIL AFTER CREATE PERSON
                personEmail1.setPersonId(personId);
                personEmailDao.create(personEmail1, connection);
                if(hasEmail2){
                    personEmail2.setPersonId(personId);
                    personEmailDao.create(personEmail2, connection);
                }
                
                /**
                 * SAVE PERSON AS AUTHOR OR READER
                */
                if("AUTHOR".equals(request.getParameter("personType"))) {
                    AuthorModel author = new AuthorModel();
                    author.setPersonId(personId);
                    new AuthorDAO().create(author, connection);
                } else {
                    ReaderModel reader = new ReaderModel();
                    reader.setPersonId(personId);
                    new ReaderDAO().create(reader, connection);
                }
            }
            else { 
                    // Update the person phone
                    personPhone1.setPersonPhoneId(Integer.parseInt(request.getParameter("phone1Id")));
                    personPhoneDao.update(personPhone1, connection);
                    if(hasPhone2) {
                        personPhone2.setPersonPhoneId(Integer.parseInt(request.getParameter("phone2Id")));
                        personPhoneDao.update(personPhone2, connection);
                    }
                    
                    // Update the person email
                    personEmail1.setPersonEmailId(Integer.parseInt(request.getParameter("email1Id")));
                    personEmailDao.update(personEmail1, connection);
                    if(hasEmail2) {
                        personEmail2.setPersonEmailId(Integer.parseInt(request.getParameter("email2Id")));
                        personEmailDao.update(personEmail2, connection);
                    }
                    
                    // Update the person address
                    address.setAddressId(Integer.parseInt(request.getParameter("addressId")));
                    addressDao.update(address, connection);
                    
                    // Update the person
                    person.setPersonId(Integer.parseInt(request.getParameter("personId")));
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
    public String getServletInfo() {
        return "Short description";
    }
}
