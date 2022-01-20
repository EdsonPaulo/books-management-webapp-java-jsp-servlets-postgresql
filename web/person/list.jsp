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
        DBConnection connection = null;
        try {
            String query = "SELECT pessoa.pk_pessoa, pessoa.nome, pessoa.sobrenome, sexo.nome AS genero,"
                    + "pessoa.bi, pessoa.telefone, pessoa.email, pessoa.data_nasc "
                    + "FROM pessoa INNER JOIN sexo ON pessoa.fk_sexo = sexo.pk_sexo;";

            connection = new DBConnection();
            ResultSet resultSet = connection.getConnection().createStatement().executeQuery(query);
    %>

    <body>
        <%@ include file="../partials/navbar.jsp" %>  

        
        <a href="<%=request.getContextPath()%>" class="btn btn-primary btn-sm m-4"><< Voltar</a>
        <a href="<%=request.getContextPath()%>/person/form.jsp" class="btn btn-primary m-4 float-right">+ Adicionar nova pessoa</a>
 
        <div class="h-100 container-fluid d-flex justify-content-center align-items-start">
            <div class="card p-5 table-responsive-lg" style="width: 100%;">
                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Nome</th>                            
                            <th scope="col">Sobrenome</th>
                            <th scope="col">Sexo</th>
                            <th scope="col">BI</th>
                            <th scope="col">Telefone</th>
                            <th scope="col">Email</th>
                            <th scope="col">Data de nasc.</th>
                            <th scope="col"></th>
                            <th scope="col">Accões</th>                            
                            <th scope="col"></th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            StringBuilder htmlBuilder = new StringBuilder();
                            while (resultSet.next()) {
                                htmlBuilder.append("<tr>");
                                htmlBuilder.append("<th scope=\"row\">0" + resultSet.getInt(1) + "</th>");
                                htmlBuilder.append("<td>" + resultSet.getString(2) + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getString(3) + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getString(4) + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getString(5) + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getString(6) + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getString(7) + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getTimestamp(8).toLocalDateTime().toLocalDate() + "</td>");

                                htmlBuilder.append("<td><a class=\"btn btn-info btn-sm text-white\" href=\"" + request.getContextPath() + "/person-servlet?id="
                                        + resultSet.getInt(1) + "&action=view\">Visualizar</a></td>");

                                htmlBuilder.append("<td><a class=\"btn btn-warning btn-sm text-white\" href=\"" + request.getContextPath() + "/person-servlet?id="
                                        + resultSet.getInt(1) + "&action=edit\">Editar</a></td>");

                                htmlBuilder.append("<td><a class=\"btn btn-danger btn-sm text-white\" href=\"" + request.getContextPath() + "/person-servlet?id="
                                        + resultSet.getInt(1) + "&action=delete\">Remover</a></td>");

                                htmlBuilder.append("</tr>");
                            }
                        %>
                        <%= htmlBuilder%>
                    </tbody>
                </table>


            </div>
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
    <script>

    </script>

</html>