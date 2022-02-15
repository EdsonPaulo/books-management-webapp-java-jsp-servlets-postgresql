<%-- Document : book/list Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="java.util.List"%>
<%@page import="ucan.dao.BookDAO"%>
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
            String query = "SELECT requisicao.pk_requisicao AS id, livro.nome AS book, livro.num_edicao AS editionNum, "
                    + "pessoa.nome AS reader, pessoa.sobrenome AS surname, requisicao.data_requisicao AS requestDate, requisicao.data_entrega AS returnDate FROM requisicao "
                    + " INNER JOIN livro ON requisicao.fk_livro = livro.pk_livro "
                    + " INNER JOIN leitor ON requisicao.fk_leitor = leitor.pk_leitor "
                    + " INNER JOIN pessoa ON leitor.fk_pessoa = pessoa.pk_pessoa;";

            connection = new DBConnection();
            ResultSet resultSet = connection.getConnection().createStatement().executeQuery(query);
    %>

    <body>
        <%@ include file="../partials/navbar.jsp" %>  

        <a href="<%=request.getContextPath()%>" class="btn btn-primary btn-sm m-4"><< Voltar</a>
        <a href="<%=request.getContextPath()%>/book-request/new.jsp" class="btn btn-primary m-4 float-right">+ Adicionar nova requisição</a>

        <div class="h-100 container-fluid d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3 table-responsive-lg" style="width: 100%;">

                <h5 class="text-center mb-3">Requisições de Livros</h5>

                <table class="table table-striped table-sm">
                    <thead>
                        <tr>
                            <th scope="col">ID</th>
                            <th scope="col">Livro</th>                            
                            <th scope="col">Leitor/Cliente</th>
                            <th scope="col">Data da requisição</th>
                            <th scope="col">Data limite de entrega</th>
                            <th scope="col">Accões</th>                            
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            StringBuilder htmlBuilder = new StringBuilder();
                            if (!resultSet.isBeforeFirst()) {
                                htmlBuilder.append("<tr><td colspan=\"8\"> <h6 class=\"my-5 text-muted text-center\">Sem dados!</h6></tr></td>");
                            }

                            while (resultSet.next()) {
                                htmlBuilder.append("<tr>");
                                htmlBuilder.append("<th scope=\"row\">0" + resultSet.getInt("id") + "</th>");
                                htmlBuilder.append("<td>" + resultSet.getString("book"));
                                htmlBuilder.append(" - Edição " + resultSet.getInt("editionNum") + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getString("reader") + " " + resultSet.getString("surname") + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getTimestamp("requestDate").toLocalDateTime() + "</td>");
                                htmlBuilder.append("<td>" + resultSet.getTimestamp("returnDate").toLocalDateTime() + "</td>");

                                htmlBuilder.append("<td><a class=\"btn btn-secondary btn-sm text-white\" href=\"" + request.getContextPath() + "/book-request/view.jsp?id="
                                        + resultSet.getInt("id") + "\">Visualizar</a>");

                                htmlBuilder.append(" <a class=\"btn btn-warning btn-sm text-white mx-2\" href=\"" + request.getContextPath() + "/book-request/edit.jsp?id="
                                        + resultSet.getInt("id") + "\">Editar</a>");

                                htmlBuilder.append("<a class=\"btn btn-danger btn-sm text-white\" href=\"" + request.getContextPath() + "/book-request-servlet?id="
                                        + resultSet.getInt("id") + "&action=delete\">Remover</a></td>");

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