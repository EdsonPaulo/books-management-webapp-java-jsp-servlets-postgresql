<%-- Document : defesa/form Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../partials/html-head.jsp" %>  
<html>
    <%
        HtmlObj obj = new HtmlObj();
        DBConnection connection = new DBConnection();
    %>
    <body>
        <a href="<%=request.getContextPath()%>/index.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start py-4">
            <div class="card px-5 py-3" style="width: 100%;">

                <h5 class="text-center mb-3">DEFESA - Formulario</h5>

                <form class="form-container" method="POST"
                      action="<%=request.getContextPath()%>/defesa/result.jsp">

                    <div class="row">
                        <div class="form-group col-6">
                            <label for="year1" class="required">Ano 1</label>
                            <input name="year1" id="year1" type="number" class="form-control">
                        </div>
                        
                        <div class="form-group col-6">
                            <label for="year2" class="required">Ano 2</label>
                            <input name="year2" id="year2" type="number" class="form-control">
                        </div>

                         <div class="form-group col-6">
                            <label for="initial" class="required">Inicial do Leitor</label>
                            <input name="initial" id="initial" type="text" maxlength="1" class="form-control">
                        </div>
                    
                        <div class="form-group col-6">
                            <label for="reader" class="required">Endeço da editora (comuna)</label>
                            <%=obj.getSelectBox(connection, "comuna", "communeId", -1)%>
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