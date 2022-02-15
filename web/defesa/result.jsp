<%-- Document : defesa/result Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../partials/html-head.jsp" %>  
<html>
    <%
        HtmlObj obj = new HtmlObj();
        DBConnection connection = new DBConnection();

        int ano = Integer.parseInt(request.getParameter("anoNasc"));
        int morada = Integer.parseInt(request.getParameter("provincia"));

        // PessoaDAO pDao = new PessoaDAO();
%>
    <body>
        <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3" style="width: 100%;">

                <h5 class="text-center mb-3">DEFESA - Form</h5>

                <div><%=ano%></div>

                <button type="submit" class="btn btn-primary my-2 float-right">
                    √  Confirmar
                </button>
            </div>
        </div>
    </body>

    <%  connection.closeConnection();%>
</html>