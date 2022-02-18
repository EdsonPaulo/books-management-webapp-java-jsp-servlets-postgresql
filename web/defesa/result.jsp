<%-- Document : defesa/result Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

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

            HtmlObj obj = new HtmlObj();
            connection = new DBConnection();

            int year1 = Integer.parseInt(request.getParameter("year1"));
            int year2 = Integer.parseInt(request.getParameter("year2"));
            String initial = request.getParameter("initial");
            int communeId = Integer.parseInt(request.getParameter("communeId"));

            String queryBook = "SELECT livro.pk_livro AS bookId, livro.nome AS book, livro.isbn, livro.num_edicao AS editionNum, pessoa.nome as reader, "
                    + " livro.ano_lancamento AS releaseYear, categoria.nome AS category, editora.nome AS publisher "
                    + " FROM requisicao "
                    + " INNER JOIN livro ON requisicao.fk_livro = livro.pk_livro "
                    + " INNER JOIN leitor ON requisicao.fk_leitor = leitor.pk_leitor "
                    + " INNER JOIN editora ON livro.fk_editora = editora.pk_editora "
                    + " INNER JOIN pessoa ON leitor.fk_pessoa = pessoa.pk_pessoa "
                    + " INNER JOIN morada ON editora.fk_morada = morada.pk_morada "
                    + " INNER JOIN comuna ON morada.fk_comuna = comuna.pk_comuna "
                    + " INNER JOIN categoria ON livro.fk_categoria = categoria.pk_categoria "
                    + " WHERE EXTRACT(YEAR FROM pessoa.data_nasc) >= '" + year1 + "' AND EXTRACT(YEAR FROM pessoa.data_nasc) <= '" + year2
                    + "' AND pessoa.nome LIKE '" + initial + "%'" + " AND morada.fk_comuna = '" + communeId + "';";

            ResultSet resultSet = connection.getConnection().createStatement().executeQuery(queryBook);
    %>
    <body>
        <a href="<%=request.getContextPath()%>/defesa/form.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3" style="width: 100%;">

                <h5 class="text-center mb-3">DEFESA - Resultados</h5>

                <div>

                    <table class="table table-striped table-sm">
                        <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">Título</th>                            
                                <th scope="col">ISBN</th>
                                <th scope="col">Categoria</th>
                                <th scope="col">Ano</th>
                                <th scope="col">Editora</th>                                

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
                                    htmlBuilder.append("<th scope=\"row\">0" + resultSet.getInt("bookId") + "</th>");
                                    htmlBuilder.append("<td>" + resultSet.getString("book"));
                                    htmlBuilder.append(" - Edição " + resultSet.getInt("editionNum") + "</td>");
                                    htmlBuilder.append("<td>" + resultSet.getString("isbn") + "</td>");
                                    htmlBuilder.append("<td>" + resultSet.getString("category") + "</td>");
                                    htmlBuilder.append("<td>" + resultSet.getString("releaseYear") + "</td>");
                                    htmlBuilder.append("<td>" + resultSet.getString("publisher") + "</td>");

                                    htmlBuilder.append("<td><a class=\"btn btn-secondary btn-sm text-white\" href=\"" + request.getContextPath() + "/book/view.jsp?id="
                                            + resultSet.getInt("bookId") + "\">Visualizar</a>");
                                    htmlBuilder.append("</tr>");
                                }
                            %>
                            <%= htmlBuilder%>

                        </tbody>
                    </table>
                </div>
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