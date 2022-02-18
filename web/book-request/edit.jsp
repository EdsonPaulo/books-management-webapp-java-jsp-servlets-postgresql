<%-- Document : person/edit Created on : 08/01/2022, 23:38:26 Author : edsonpaulo --%>

<%@page import="ucan.models.BookRequestModel"%>
<%@page import="ucan.dao.BookRequestDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="ucan.conection.DBConnection"%>
<%@page import="ucan.utils.HtmlObj" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%@ include file="../partials/html-head.jsp" %>  
<html>
    <%
        HtmlObj obj = new HtmlObj();
        DBConnection connection = new DBConnection();
        BookRequestDAO bookRequestDao = new BookRequestDAO();
        BookRequestModel bookRequest = null;

        try {
            int id = Integer.parseInt(request.getParameter("id"));
            bookRequest = bookRequestDao.getBookRequestById(id, connection);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    %>
    <body>
        <a href="<%=request.getContextPath()%>/book-request/list.jsp" class="btn btn-primary btn-sm m-4"><< Voltar</a>

        <div class="h-100 container d-flex justify-content-center align-items-start">
            <div class="card px-5 py-3" style="width: 100%;">

                <h5 class="text-center mb-3">Editar de Requisição</h5>

                <form class="form-container" method="POST"
                      action="<%=request.getContextPath() + "/book-request-servlet?edit=true"
                              + "&requestId=" + bookRequest.getRequestId()%>">

                    <div class="row">
                        <div class="form-group col-6">
                            <label for="book" class="required">Livro</label>
                            <%=obj.getSelectBox(connection, "livro", "book", bookRequest.getBookId())%>
                        </div>

                        <div class="form-group col-6">
                            <label for="reader" class="required">Leitor / Cliente</label>
                            <%=obj.getSelectBoxWithTableJoin(connection, "leitor", "pessoa", "reader", bookRequest.getReaderId())%>
                        </div>

                        <div class="form-group col-6">
                            <label for="requestDate" class="required">Data da requisição</label>
                            <input name="requestDate" id="requestDate" type="date" class="form-control" required value="<%= bookRequest.getRequestDate() %>">
                        </div>       

                        <div class="form-group col-6">
                            <label for="returnDate" class="required">Data limite para retorno</label>
                            <input name="returnDate" id="returnDate" type="date" class="form-control" required value="<%= bookRequest.getReturnDate()%>>
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