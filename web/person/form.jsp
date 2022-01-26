<%-- Document : person Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

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
        Boolean isEditing = request.getParameter("id") != null;

        PersonDAO personDao = new PersonDAO();
        PersonModel person = null;
        AddressModel address = null;
        List<PersonPhoneModel> personPhones = null;
        List<PersonEmailModel> personEmails = null;

        if (isEditing) {
            try {
                int id = Integer.parseInt(request.getParameter("id"));
                person = personDao.getPersonById(id, connection);
                address = new AddressDAO().getAddressById(person.getAddressId(), connection);
                personPhones = new PersonPhoneDAO().getAllByPersonId(person.getPersonId(), connection);
                personEmails = new PersonEmailDAO().getAllByPersonId(person.getPersonId(), connection);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    %>
    <body>
        <a href="<%=request.getContextPath()%>/person/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card p-5" style="width: 100%;">
                <form class="form-container" method="POST"
                      action="<%=request.getContextPath()%>/person-servlet<%= isEditing ? "?action=edit" : ""%>">

                    <input name="personId" value="<%=isEditing ? person.getPersonId() : ""%>" style="opacity: 0">
                    <input name="addressId" value="<%=isEditing ? address.getAddressId() : ""%>" style="opacity: 0">

                    <input name="phone1Id" style="opacity: 0" value="<%=isEditing && personPhones.size() > 0 ? personPhones.get(0).getPersonPhoneId() : ""%>"  >
                    <input name="email1Id" style="opacity: 0" value="<%=isEditing && personEmails.size() > 0 ? personEmails.get(0).getPersonEmailId() : ""%>">

                    <input name="phone2Id" style="opacity: 0" value="<%=isEditing && personPhones.size() > 1 ? personPhones.get(1).getPersonPhoneId() : ""%>">
                    <input name="email2Id" style="opacity: 0" value="<%=isEditing && personEmails.size() > 1 ? personEmails.get(1).getPersonEmailId() : ""%>">

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="personType" class="required">Tipo</label>
                            <select id="personType" name="personType" class="form-control" disabled="<%=isEditing%>"> 
                                <option>Selecione a funcão da pessoa</option>                                
                                <option value="AUTHOR" selected="<%=personDao.isAuthor(person.getPersonId(), connection)%>">1 - Autor</option>
                                <option value="READER" selected="<%=personDao.isReader(person.getPersonId(), connection)%>">2 - Leitor</option>
                            </select>
                        </div> 
                        <div class="form-group col-4">
                            <label for="name" class="required">Nome</label>
                            <input name="name" id="name" value="<%=isEditing && person != null ? person.getName() : ""%>"  type="text" class="form-control" required>
                        </div>
                        <div class="form-group  col-4">
                            <label for="surname" class="required">Sobrenome</label>
                            <input name="surname" id="surname" value="<%=isEditing && person != null ? person.getSurname() : ""%>" type="text" class="form-control" required>
                        </div> 
                    </div>

                    <div class="row d-flex justify-content-between">
                        <div class="form-group col-4">
                            <label for="bi" class="required">BI</label>
                            <input name="bi" id="bi" value="<%=isEditing && person != null ? person.getBi() : ""%>" type="text" class="form-control" required>
                        </div>
                        <div class="form-group col-4">
                            <label for="birthDate" class="required">Data Nascimento</label>
                            <input name="birthDate" id="birthDate" type="date" value="<%=isEditing && person != null ? person.getBirthDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : ""%>" class="form-control" required>
                        </div> 
                        <div class="form-group col-4">
                            <label for="gender" class="required">Genero</label>
                            <%=obj.getSelectBox(connection, "sexo", "gender", isEditing && person != null ? person.getGenderId() : -1)%>
                        </div>
                    </div>

                    <div class="row d-flex justify-content-between">
                        <div class="form-group col-4">
                            <label for="phone1" class="required">Telefone principal</label>
                            <input name="phone1" id="phone1" type="tel" value="<%=isEditing && personPhones.size() > 0 ? personPhones.get(0).getPhone() : ""%>"class="form-control" required>
                        </div>
                        <div class="form-group col-4">
                            <label for="email1" class="required">Email principal</label>
                            <input name="email1" id="email1" type="email" value="<%=isEditing && personEmails.size() > 0 ? personEmails.get(0).getEmail() : ""%>" class="form-control" required>
                        </div>            
                    </div>

                    <div class="row d-flex justify-content-between">
                        <div class="form-group col-4">
                            <label for="phone2">Telefone secundário (opcional)</label>
                            <input name="phone2" id="phone2" type="tel" value="<%=isEditing && personPhones.size() > 1 ? personPhones.get(1).getPhone() : ""%>"class="form-control">
                        </div>   
                        <div class="form-group col-4">
                            <label for="email2">Email secundário (opcional)</label>
                            <input name="email2" id="email2" type="email" value="<%=isEditing && personEmails.size() > 1 ? personEmails.get(1).getEmail() : ""%>" class="form-control">
                        </div>                        
                    </div>

                    <p class="h5 my-2 text-center text-muted">Morada</p> 

                    <div class="row mt-3" style="display: <%=isEditing ? "none" : "flex"%>">
                        <div class="form-group col-4">
                            <label for="country" class="required">Pais</label>
                            <%=obj.getSelectBox(connection, "pais", "country", -1)%>
                        </div>
                        <div class="form-group col-4">
                            <label for="province" class="required">Provincia</label>
                            <select id="province" name="province" class="form-control"> 
                                <option>-- Selecione um pais primeiro --</option>
                            </select>
                        </div>     
                        <div class="form-group  col-4">
                            <label for="municipality" class="required">Municipio</label>
                            <select id="municipality" name="municipality" class="form-control"> 
                                <option>-- Selecione uma provincia primeiro --</option>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group  col-3">
                            <label for="commune" class="required">Comuna</label>
                            <%=isEditing ? obj.getSelectBox(connection, "comuna", "commune", address != null ? address.getCommuneId() : -1)
                                    : "<select id=\"commune\" name=\"commune\" class=\"form-control\"><option>--Selecione--</option></select>"%>
                        </div>
                        <div class="form-group col-3">
                            <label for="district">Bairro</label>
                            <input name="district" id="district" type="text" value="<%=isEditing && person != null ? address.getDistrict() : ""%>" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="street">Rua</label>
                            <input name="street" id="street" type="text" value="<%=isEditing && person != null ? address.getStreet() : ""%>" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="houseNum">Nº da casa</label>
                            <input name="houseNum" id="houseNum" type="number" value="<%=isEditing && person != null ? address.getHouseNum() : ""%>" class="form-control">
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

    <script type="text/javascript">

        // get current context path or base url path name 
        function getContextPath() {
            return window.location.pathname.substring(0, window.location.pathname.indexOf("/", 2));
        }

        function fetchList(operation, id) {
            const selectElement = document.getElementById(operation);

            fetch(getContextPath() + '/person-servlet?' + new URLSearchParams({operation, id}), {method: 'GET'})
                    .then(response => response.json())
                    .then(data => {
                        data.forEach((item) => {
                            selectElement.options[selectElement.options.length] = new Option(item.name, item[operation + 'Id']);
                        });
                    })
                    .catch(() => {
                        selectElement.options[0] = new Option('Sem dados disponíveis', '');
                    });
        }

        function clearProvinceSelect() {
            const provinceNode = document.getElementById("province");
            while (provinceNode.firstChild)
                provinceNode.removeChild(provinceNode.lastChild);
            provinceNode.options[provinceNode.options.length] = new Option('Selecione uma provincia');
        }

        function clearMunicipalitySelect() {
            const municipalityNode = document.getElementById("municipality");
            while (municipalityNode.firstChild)
                municipalityNode.removeChild(municipalityNode.lastChild);
            municipalityNode.options[municipalityNode.options.length] = new Option('Selecione um município');
        }

        function clearCommuneSelect() {
            const communeNode = document.getElementById("commune");
            while (communeNode.firstChild)
                communeNode.removeChild(communeNode.lastChild);
            communeNode.options[communeNode.options.length] = new Option('Selecione uma comuna');
        }

        // -- Handling changes of selectboxes --

        document.getElementById('country').addEventListener('change', function (event) {
            clearProvinceSelect();
            clearMunicipalitySelect();
            clearCommuneSelect();
            fetchList('province', event.target.value);
        });

        document.getElementById('province').addEventListener('change', function (event) {
            clearMunicipalitySelect();
            clearCommuneSelect();
            fetchList('municipality', event.target.value);
        });

        document.getElementById('municipality').addEventListener('change', function (event) {
            clearCommuneSelect();
            fetchList('commune', event.target.value);
        });
    </script>
</html>