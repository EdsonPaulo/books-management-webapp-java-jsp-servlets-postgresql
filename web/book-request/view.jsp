<%-- Document : book/view Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="java.util.Vector"%>
<%@page import="ucan.dao.BookDAO"%>
<%@page import="java.util.List"%>
<%@page import="ucan.utils.Helpers"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.Date"%>
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
            String queryBook = "SELECT livro.pk_livro AS id, livro.nome AS name, livro.isbn AS isbn, livro.num_edicao AS editionNum, "
                    + "livro.num_paginas AS numPages, livro.ano_lancamento AS releaseYear, categoria.nome AS category, editora.nome AS publisher, "
                    + " localizacao_livro.nome AS location, localizacao_livro.num_prateleira AS rackNum, estado.nome AS status FROM livro "
                    + " INNER JOIN categoria ON livro.fk_categoria = categoria.pk_categoria "
                    + " INNER JOIN editora ON livro.fk_editora = editora.pk_editora "
                    + " INNER JOIN estado ON livro.fk_estado = estado.pk_estado "
                    + " INNER JOIN localizacao_livro ON livro.fk_localizacao = localizacao_livro.pk_localizacao_livro "
                    + " WHERE livro.pk_livro = " + id + ";";

            connection = new DBConnection();
            ResultSet resultSet = connection.getConnection().createStatement().executeQuery(queryBook);

            BookDAO bookDao = new BookDAO();
            Vector<String> authors = bookDao.getBookAuthors(id, connection);
            Vector<String> tags = bookDao.getBookTags(id, connection);
    %>

    <body>
        <a href="<%=request.getContextPath()%>/book/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <a href="<%=request.getContextPath()%>/book-servlet?action=delete&id=<%= id%>" class="btn btn-danger btn-sm m-4 float-right">X Eliminar</a>

        <a href="<%=request.getContextPath()%>/book/edit.jsp?id=<%= id%>" class="btn btn-warning btn-sm mt-4 float-right"> Editar</a>

        <div class="h-100 container">
            <div class="card p-5" style="width: 100%;">
                <div class="d-flex flex-row flex-wrap">
                    <%
                        StringBuilder htmlBuilder = new StringBuilder();
                        while (resultSet.next()) {
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>ID</h6>  <h5>" + resultSet.getInt("id") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Título</h6> <h5>" + resultSet.getString("name") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Edição</h6> <h5>" + resultSet.getString("editionNum") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>ISBN</h6> <h5>" + resultSet.getString("isbn") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Nº Páginas</h6> <h5>" + resultSet.getString("numPages") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Ano de lançamento</h6> <h5>" + resultSet.getString("releaseYear") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Categoria</h6> <h5>" + resultSet.getString("category") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Editora</h6> <h5>" + resultSet.getString("publisher") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Nº da prateleira: </h6><h5>" + resultSet.getString("rackNum") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Chave na prateleira: </h6> <h5>" + resultSet.getString("location") + "</h5></div>");
                            htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Estado</h6> <h5>" + resultSet.getString("status") + "</h5></div>");
                        }
                    %>
                    <%= htmlBuilder%>
                </div>   

                <div class="mt-3 d-flex flex-row flex-wrap">
                    <%
                        htmlBuilder = new StringBuilder();

                        htmlBuilder.append("<div class=\"mr-4\"><h6>Descritores/Tags</h6><h5>");
                        for (String tag : tags) {
                            htmlBuilder.append("<span class=\"mr-2\"> * " + tag + "</span><br>");
                        }
                        htmlBuilder.append("</h5></div>");

                        htmlBuilder.append("<div class=\"mr-5\"><h6>Autores</h6><h5>");
                        for (String author : authors) {
                            htmlBuilder.append("<span class=\"mr-2\"> * " + author + "</span>");
                        }
                        htmlBuilder.append("</h5></div>");
                    %>
                    <%= htmlBuilder%>
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
</html>