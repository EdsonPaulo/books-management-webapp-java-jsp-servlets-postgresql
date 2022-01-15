<%-- Document : person Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <%@ include file="HtmlHead.jsp" %>  
    <%
        HtmlObj obj = new HtmlObj();
        DBConnection connection = new DBConnection();
    %>

    <body>
        <button type="button" class="btn btn-primary btn-sm m-4">
            <a href="." class="text-light text-decoration-none"><< Voltar</a>
        </button>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card p-5" style="width: 100%;">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Nome</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%=obj.getTableRow(connection, "pessoa")%>
                    </tbody>
                </table>

                <button type="submit" class="btn btn-primary my-2 float-right">
                    Adicionar nova pessoa
                     <a href="./formulario-pessoa.jsp" class="text-light text-decoration-none"><< Voltar</a>
                </button>
            </div>
        </div>
    </body>
    
     <%  connection.closeConnection();  %>
</html>