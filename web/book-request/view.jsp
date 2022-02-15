<%-- Document : book-request/view Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

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
            String queryBook = "SELECT requisicao.pk_requisicao AS id, requisicao.data_requisicao AS requestDate, requisicao.data_entrega AS returnDate, "
                    + " livro.nome AS book, livro.isbn, livro.num_edicao AS editionNum, "
                    + " livro.ano_lancamento AS releaseYear, categoria.nome AS category, editora.nome AS publisher, "
                    + " localizacao_livro.nome AS location, localizacao_livro.num_prateleira AS rackNum, "
                    + " pessoa.nome AS reader, pessoa.sobrenome AS surname, pessoa.bi, "
                    + " morada.num_casa AS house, morada.rua AS street, morada.bairro AS district, comuna.nome AS commune FROM requisicao"
                    + " INNER JOIN livro ON requisicao.fk_livro = livro.pk_livro "
                    + " INNER JOIN leitor ON requisicao.fk_leitor = leitor.pk_leitor "
                    + " INNER JOIN pessoa ON leitor.fk_pessoa = pessoa.pk_pessoa "
                    + " INNER JOIN sexo ON pessoa.fk_sexo = sexo.pk_sexo "
                    + " INNER JOIN morada ON pessoa.fk_morada = morada.pk_morada "
                    + " INNER JOIN comuna ON morada.fk_comuna = comuna.pk_comuna "
                    + " INNER JOIN categoria ON livro.fk_categoria = categoria.pk_categoria "
                    + " INNER JOIN editora ON livro.fk_editora = editora.pk_editora "
                    + " INNER JOIN localizacao_livro ON livro.fk_localizacao = localizacao_livro.pk_localizacao_livro "
                    + " WHERE requisicao.pk_requisicao = " + id + ";";

            connection = new DBConnection();
            ResultSet resultSet = connection.getConnection().createStatement().executeQuery(queryBook);

    %>

    <body>
        <a href="<%=request.getContextPath()%>/book-request/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <a href="<%=request.getContextPath()%>/book-request-servlet?action=delete&id=<%= id%>" class="btn btn-danger btn-sm m-4 float-right">X Eliminar</a>

        <a href="<%=request.getContextPath()%>/book-request/edit.jsp?id=<%= id%>" class="btn btn-warning btn-sm mt-4 float-right"> Editar</a>

        <div class="h-100 container">
            <div class="card p-5" style="width: 100%;">
                <%
                    StringBuilder htmlBuilder = new StringBuilder();
                    while (resultSet.next()) {
                        htmlBuilder.append("<h6 class=\"text-muted\">DETALHES DA REQUISIÇÃO</h6>");
                        htmlBuilder.append("<div class=\"mb-4 d-flex flex-row flex-wrap\">");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>ID</h6>  <h5>" + resultSet.getInt("id") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Data da requisição</h6><h5>" + resultSet.getTimestamp("requestDate").toLocalDateTime().toLocalDate() + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Data de retorno/devolução</h6><h5>" + resultSet.getTimestamp("returnDate").toLocalDateTime().toLocalDate() + "</h5></div>");
                        htmlBuilder.append("</div>");

                        htmlBuilder.append("<h6 class=\"text-muted\">DETALHES DO LIVRO</h6>");
                        htmlBuilder.append("<div class=\"mb-4 d-flex flex-row flex-wrap\">");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Título</h6> <h5>" + resultSet.getString("book") + " - Edição " + resultSet.getString("editionNum") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>ISBN</h6> <h5>" + resultSet.getString("isbn") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Ano de lançamento</h6> <h5>" + resultSet.getString("releaseYear") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Categoria</h6> <h5>" + resultSet.getString("category") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Editora</h6> <h5>" + resultSet.getString("publisher") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Nº da prateleira: </h6><h5>" + resultSet.getString("rackNum") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Chave na prateleira: </h6> <h5>" + resultSet.getString("location") + "</h5></div>");
                        htmlBuilder.append("</div>");

                        htmlBuilder.append("<h6 class=\"text-muted\">DETALHES DO LEITOR/CLIENTE</h6>");
                        htmlBuilder.append("<div class=\"mb-4 d-flex flex-row flex-wrap\">");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Nome</h6> <h5>" + resultSet.getString("reader") + " " + resultSet.getString("surname") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>BI</h6> <h5>" + resultSet.getString("bi") + "</h5></div>");
                        htmlBuilder.append("<div class=\"mr-4 mb-3\"><h6>Endereço: </h6> <h5>Casa "
                                + resultSet.getString("house") + ", "
                                + resultSet.getString("street") + " - "
                                + resultSet.getString("district") + ", "
                                + resultSet.getString("commune")
                                + "</h5></div>");

                        htmlBuilder.append("</div>");
                    }
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