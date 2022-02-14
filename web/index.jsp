<%-- 
    Document   : index
    Created on : 08/01/2022, 20:14:13
    Author     : edsonpaulo
--%>

<%@page import="ucan.utils.HtmlObj"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Biblioteca UCAN - By EdsonPaulo</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./assets/css/bootstrap.min.css"/>         
        <link rel="stylesheet" href="./assets/css/styles.css"/> 
        <script src="./assets/js/jquery-3.4.1.min.js" ></script>
        <script src="./assets/js/popper.min.js" ></script>
        <script src="./assets/js/bootstrap.min.js"></script>
    </head>

    <body>
        <%@ include file="./partials/navbar.jsp" %>  

        <div class="container card my-5 p-5">
            <div class="row">
                <%=HtmlObj.generateHomeSectionLink("Pessoas", request.getContextPath() + "/person/list.jsp")%>

                <%=HtmlObj.generateHomeSectionLink("Autores", request.getContextPath() + "/author/list.jsp")%>

                <%=HtmlObj.generateHomeSectionLink("Leitores", request.getContextPath() + "/reader/list.jsp")%>
            </div>

            <div class="row">
                <%=HtmlObj.generateHomeSectionLink("Editoras", request.getContextPath() + "/publisher/list.jsp")%>
                
                <%=HtmlObj.generateHomeSectionLink("Livros", request.getContextPath() + "/book/list.jsp")%>
                
                <%=HtmlObj.generateHomeSectionLink("Empréstimos de livros", request.getContextPath() + "/book-request/list.jsp")%>
            </div>
        </div>
    </div>
</body>
</html>
