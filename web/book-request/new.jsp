<%-- Document : book/new Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

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
        <a href="<%=request.getContextPath()%>/book/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3" style="width: 100%;">

                <h5 class="text-center mb-3">Novo Livro</h5>

                <form class="form-container" method="POST"
                      action="<%=request.getContextPath()%>/book-servlet">

                    <div class="row">
                        <div class="form-group col-4">
                            <label for="name" class="required">Título do livro</label>
                            <input name="name" id="name" type="text" class="form-control" required>
                        </div>
                        <div class="form-group col-4">
                            <label for="isbn" class="required">ISBN</label>
                            <input name="isbn" id="isbn" type="text" class="form-control" required>
                        </div>
                        <div class="form-group col-4">
                            <label for="category" class="required">Categoria</label>
                            <%=obj.getSelectBox(connection, "categoria", "category", -1)%>
                        </div>
                        <div class="form-group col-3">
                            <label for="numPages">Nº Páginas</label>
                            <input name="numPages" id="numPages" type="number" maxlength="4" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="editionNum">Nº Edição</label>
                            <input name="editionNum" id="editionNum" type="number" maxlength="4" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="releaseYear">Ano Lançamento</label>
                            <input name="releaseYear" id="releaseYear" maxlength="4" type="number" class="form-control">
                        </div>
                        <div class="form-group col-3">
                            <label for="tags" class="required">Descritores (tags)</label>
                            <%=obj.getMultipleSelectBox(connection, "descritores", "tags", new int[1])%>
                        </div>
                        <div class="form-group col-3">
                            <label for="authors" class="required">Autor(es)</label>
                            <%=obj.getMultipleSelectWithTableJoin(connection, "autor", "pessoa", "authors", new int[1])%>
                        </div>
                        <div class="form-group col-3">
                            <label for="publisher" class="required">Editora</label>
                            <%=obj.getSelectBox(connection, "editora", "publisher", -1)%>
                        </div>
                        <div class="form-group col-3">
                            <label for="location" class="required">Localização</label>
                            <%=obj.getSelectBox(connection, "localizacao_livro", "location", -1)%>
                        </div>
                        <div class="form-group col-3">
                            <label for="status" class="required">Estado</label>
                            <%=obj.getSelectBox(connection, "estado", "status", -1)%>
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