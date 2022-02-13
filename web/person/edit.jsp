<%-- Document : person/edit Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.dao.PersonEmailDAO"%>
<%@page import="ucan.models.PersonEmailModel"%>
<%@page import="ucan.dao.PersonPhoneDAO"%>
<%@page import="ucan.models.PersonPhoneModel"%>
<%@page import="java.util.List"%>
<%@page import="ucan.models.CountryModel"%>
<%@page import="ucan.dao.CountryDAO"%>
<%@page import="ucan.dao.CountryDAO"%>
<%@page import="ucan.dao.ProvinceDAO"%>
<%@page import="ucan.models.ProvinceModel"%>
<%@page import="ucan.dao.MunicipalityDAO"%>
<%@page import="ucan.models.MunicipalityModel"%>
<%@page import="ucan.dao.CommuneDAO"%>
<%@page import="ucan.models.CommuneModel"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="ucan.dao.AddressDAO"%>
<%@page import="ucan.models.AddressModel"%>
<%@page import="ucan.dao.PersonDAO"%>
<%@page import="ucan.models.PersonModel"%>
<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../partials/html-head.jsp" %>  
<html>
    <%
        HtmlObj obj = new HtmlObj();
        DBConnection connection = new DBConnection();

        PersonDAO personDao = new PersonDAO();
        PersonModel person = null;
        AddressModel address = null;
        List<PersonPhoneModel> personPhones = null;
        List<PersonEmailModel> personEmails = null;
        boolean isAuthor = false;
        boolean isReader = false;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            isAuthor = personDao.isAuthor(id, connection);
            isReader = personDao.isReader(id, connection);
            person = personDao.getPersonById(id, connection);
            address = new AddressDAO().getAddressById(person.getAddressId(), connection);
            personPhones = new PersonPhoneDAO().getAllByPersonId(person.getPersonId(), connection);
            personEmails = new PersonEmailDAO().getAllByPersonId(person.getPersonId(), connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    %>
    <body>
        <a href="<%=request.getContextPath()%>/person/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3" style="width: 100%;">
                
                <h5 class="text-center mb-3">Editar de Pessoas</h5>

                <form class="form-container" method="POST"
                      action="<%=request.getContextPath() + "/person-servlet?edit=true"
                              + "&personId=" + person.getPersonId()
                              + "&addressId=" + address.getAddressId()
                              + "&phone1Id=" + personPhones.get(0).getPersonPhoneId()
                              + "&email1Id=" + personEmails.get(0).getPersonEmailId()%>">

                    <input name="phone2Id" style="opacity: 0" value="<%=personPhones.size() > 1 ? personPhones.get(1).getPersonPhoneId() : ""%>">
                    <input name="email2Id" style="opacity: 0" value="<%=personEmails.size() > 1 ? personEmails.get(1).getPersonEmailId() : ""%>">

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="personType" class="required">Tipo</label>
                            <select id="personType" name="personType" class="form-control" disabled> 
                                <option>Selecione a funcão da pessoa</option>                                
                                <option value="AUTHOR" <%=isAuthor ? "selected" : ""%>>1 - Autor</option>
                                <option value="READER" <%=isReader ? "selected" : ""%>>2 - Leitor</option>
                            </select>
                        </div> 
                        <div class="form-group col-4">
                            <label for="name" class="required">Nome</label>
                            <input name="name" id="name" value="<%=person.getName()%>"  type="text" class="form-control" required>
                        </div>
                        <div class="form-group  col-4">
                            <label for="surname" class="required">Sobrenome</label>
                            <input name="surname" id="surname" value="<%=person.getSurname()%>" type="text" class="form-control" required>
                        </div> 
                    </div>

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="bi" class="required">BI</label>
                            <input name="bi" id="bi" value="<%=person.getBi()%>" type="text" class="form-control" required>
                        </div>
                        <div class="form-group col-4">
                            <label for="birthDate" class="required">Data Nascimento</label>
                            <input name="birthDate" id="birthDate" type="date" value="<%=person.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))%>" class="form-control" required>
                        </div> 
                        <div class="form-group col-4">
                            <label for="gender" class="required">Genero</label>
                            <%=obj.getSelectBox(connection, "sexo", "gender", person.getGenderId())%>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="phone1" class="required">Telefone principal</label>
                            <input name="phone1" id="phone1" type="tel" value="<%= personPhones.size() > 0 ? personPhones.get(0).getPhone() : ""%>" class="form-control" required>
                        </div>
                        <div class="form-group col-4">
                            <label for="phone2">Telefone secundário (opcional)</label>
                            <input name="phone2" id="phone2" type="tel" value="<%= personPhones.size() > 1 ? personPhones.get(1).getPhone() : ""%>" class="form-control">
                        </div>  

                    </div>

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="email1" class="required">Email principal</label>
                            <input name="email1" id="email1" type="email" value="<%= personEmails.size() > 0 ? personEmails.get(0).getEmail() : ""%>" class="form-control" required>
                        </div>    
                        <div class="form-group col-4">
                            <label for="email2">Email secundário (opcional)</label>
                            <input name="email2" id="email2" type="email" value="<%= personEmails.size() > 1 ? personEmails.get(1).getEmail() : ""%>" class="form-control">
                        </div>                        
                    </div>

                    <p class="h5 my-2 text-center text-muted">Morada</p> 

                    <div class="row">
                        <div class="form-group col-3">
                            <label for="commune" class="required">Comuna</label>
                            <%=obj.getSelectBox(connection, "comuna", "commune", address != null ? address.getCommuneId() : -1)%>
                        </div>
                        <div class="form-group col-3">
                            <label for="district">Bairro</label>
                            <input name="district" id="district" type="text" value="<%=address.getDistrict()%>" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="street">Rua</label>
                            <input name="street" id="street" type="text" value="<%=address.getStreet()%>" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="houseNum">Nº da casa</label>
                            <input name="houseNum" id="houseNum" type="number" value="<%=address.getHouseNum()%>" class="form-control">
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary my-2 float-right">
                        √  Confirmar
                    </button>
                </form>
            </div>
        </div>
    </body>
    <%  connection.closeConnection();%>
</html>