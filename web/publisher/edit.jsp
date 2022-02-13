<%-- Document : publisher/edit Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.dao.PublisherEmailDAO"%>
<%@page import="ucan.models.PublisherEmailModel"%>
<%@page import="ucan.dao.PublisherPhoneDAO"%>
<%@page import="ucan.models.PublisherPhoneModel"%>
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
<%@page import="ucan.dao.PublisherDAO"%>
<%@page import="ucan.models.PublisherModel"%>
<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../partials/html-head.jsp" %>  
<html>
    <%
        HtmlObj obj = new HtmlObj();
        DBConnection connection = new DBConnection();

        PublisherDAO publisherDao = new PublisherDAO();
        PublisherModel publisher = null;
        AddressModel address = null;
        List<PublisherPhoneModel> publisherPhones = null;
        List<PublisherEmailModel> publisherEmails = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            publisher = publisherDao.getPublisherById(id, connection);
            address = new AddressDAO().getAddressById(publisher.getAddressId(), connection);
            publisherPhones = new PublisherPhoneDAO().getAllByPublisherId(publisher.getPublisherId(), connection);
            publisherEmails = new PublisherEmailDAO().getAllByPublisherId(publisher.getPublisherId(), connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    %>
    <body>
        <a href="<%=request.getContextPath()%>/publisher/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3" style="width: 100%;">
                
                <h5 class="text-center mb-3">Editar Editora</h5>
                                
                <form class="form-container" method="POST"
                      action="<%=request.getContextPath() + "/publisher-servlet?edit=true"
                              + "&publisherId=" + publisher.getPublisherId()
                              + "&addressId=" + address.getAddressId()
                              + "&phone1Id=" + publisherPhones.get(0).getPublisherPhoneId()
                              + "&email1Id=" + publisherEmails.get(0).getPublisherEmailId()%>">

                    <input name="phone2Id" style="opacity: 0" value="<%=publisherPhones.size() > 1 ? publisherPhones.get(1).getPublisherPhoneId() : ""%>">
                    <input name="email2Id" style="opacity: 0" value="<%=publisherEmails.size() > 1 ? publisherEmails.get(1).getPublisherEmailId() : ""%>">

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="name" class="required">Nome</label>
                            <input name="name" id="name" value="<%=publisher.getName()%>"  type="text" class="form-control" required>
                        </div>
                        <div class="form-group  col-4">
                            <label for="nif" class="required">NIF</label>
                            <input name="nif" id="nif" value="<%=publisher.getNif()%>" type="text" class="form-control" required>
                        </div> 
                         <div class="form-group col-4">
                            <label for="fax" class="required">Fax</label>
                            <input name="fax" id="fax" value="<%=publisher.getFax()%>" type="text" class="form-control" required>
                        </div>
                    </div>

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="phone1" class="required">Telefone principal</label>
                            <input name="phone1" id="phone1" type="tel" value="<%= publisherPhones.size() > 0 ? publisherPhones.get(0).getPhone() : ""%>" class="form-control" required>
                        </div>
                         <div class="form-group col-4">
                            <label for="phone2">Telefone secundário (opcional)</label>
                            <input name="phone2" id="phone2" type="tel" value="<%= publisherPhones.size() > 1 ? publisherPhones.get(1).getPhone() : ""%>" class="form-control">
                        </div>   
                    </div>

                    <div class="row">
                       <div class="form-group col-4">
                            <label for="email1" class="required">Email principal</label>
                            <input name="email1" id="email1" type="email" value="<%= publisherEmails.size() > 0 ? publisherEmails.get(0).getEmail() : ""%>" class="form-control" required>
                        </div>    
                        <div class="form-group col-4">
                            <label for="email2">Email secundário (opcional)</label>
                            <input name="email2" id="email2" type="email" value="<%= publisherEmails.size() > 1 ? publisherEmails.get(1).getEmail() : ""%>" class="form-control">
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