<%-- Document : person Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.utils.Helpers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
<%@page import="ucan.dao.GenderDAO"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../partials/html-head.jsp" %>  
<html>
    <%
        int id = Integer.parseInt(request.getParameter("id"));
        DBConnection connection = null;
        try {
            String queryPerson = "SELECT pessoa.pk_pessoa AS id, pessoa.nome AS name, pessoa.sobrenome AS surname, "
                    + "sexo.nome AS gender, pessoa.bi AS bi, telefone_pessoa.numero AS phone, email_pessoa.email AS email, pessoa.data_nasc AS birthDate, "
                    + "morada.num_casa AS house, morada.rua AS street, morada.bairro AS district, "
                    + "comuna.nome AS commune, municipio.nome AS municipality, "
                    + "provincia.nome AS province, pais.nome AS country "
                    + " FROM pessoa INNER JOIN sexo ON pessoa.fk_sexo = sexo.pk_sexo "
                    + " INNER JOIN telefone_pessoa ON telefone_pessoa.fk_pessoa = pessoa.pk_pessoa "
                    + " INNER JOIN email_pessoa ON email_pessoa.fk_pessoa = pessoa.pk_pessoa "
                    + " INNER JOIN morada ON pessoa.fk_morada = morada.pk_morada "
                    + " INNER JOIN comuna ON morada.fk_comuna = comuna.pk_comuna "
                    + " INNER JOIN municipio ON comuna.fk_municipio = municipio.pk_municipio "
                    + " INNER JOIN provincia ON municipio.fk_provincia = provincia.pk_provincia "
                    + " INNER JOIN pais ON provincia.fk_pais = pais.pk_pais "                    
                    + " WHERE pessoa.pk_pessoa = " + id + ";";

            connection = new DBConnection();
            ResultSet resultSet = connection.getConnection().createStatement().executeQuery(queryPerson);
    %>

    <body>
        <a href="<%=request.getContextPath()%>/person/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>
        <a href="<%=request.getContextPath()%>/person-servlet?action=delete&id=<%= id%>" class="btn btn-danger btn-sm m-4 float-right">X Eliminar</a>
        <a href="<%=request.getContextPath()%>/person/form.jsp?id=<%= id%>" class="btn btn-warning btn-sm mt-4 float-right"> Editar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card p-5 d-flex flex-row flex-wrap" style="width: 100%;">
                <%
                    StringBuilder htmlBuilder = new StringBuilder();
                    while (resultSet.next()) {
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>ID</h6>  <h4>" + resultSet.getInt("id") + "</h4></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Nome</h6> <h4>" + resultSet.getString("name") + "</h4></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Sobrenome</h6> <h4>" + resultSet.getString("surname") + "</h4></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Sexo</h6> <h4>" + resultSet.getString("gender") + "</h4></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>BI</h6> <h4>" + resultSet.getString("bi") + "</h4></div>");
                        //htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Telefone</h6> <h4>" + resultSet.getString("phone") + "</h4></div>");
                        //htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Email</h6> <h4>" + resultSet.getString("email") + "</h4></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Data de nascimento</h6> <h4>" + resultSet.getTimestamp("birthDate").toLocalDateTime().toLocalDate() + "</h4></div>");

                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Endereço</h6>");
                        htmlBuilder.append("<h4> Casa nº " + resultSet.getString("house") + ", " + resultSet.getString("street"));
                        htmlBuilder.append(", " + resultSet.getString("commune") + ", " + resultSet.getString("municipality"));
                        htmlBuilder.append(", " + resultSet.getString("province") + " - " + resultSet.getString("country") + "</h4></div>");
                    }
                %>
                <%= htmlBuilder%>
            </div>
    </body>
    <%
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    %>
</html>