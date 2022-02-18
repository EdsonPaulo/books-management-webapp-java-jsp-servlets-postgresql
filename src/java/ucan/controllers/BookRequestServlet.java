package ucan.controllers;

import ucan.utils.Helpers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucan.conection.DBConnection;
import ucan.dao.BookRequestDAO;
import ucan.models.BookRequestModel;

/**
 *
 * @author edsonpaulo
 */
@WebServlet(name = "BookRequestServlet", urlPatterns = {"/book-request-servlet"})
public class BookRequestServlet extends HttpServlet {

    private DBConnection connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            connection = new DBConnection();

            if (request.getParameter("action") != null) {
                BookRequestDAO bookRequestDao = new BookRequestDAO();
                int id = Integer.parseInt(request.getParameter("id"));

                if (request.getParameter("action").equals("delete")) {
                    bookRequestDao.delete(id, connection);
                    response.sendRedirect(request.getContextPath() + "/book-request/list.jsp");
                }
            }
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            connection = new DBConnection();
            BookRequestModel bookRequest = new BookRequestModel();
            BookRequestDAO bookRequestDao = new BookRequestDAO();

            bookRequest.setBookId(Integer.parseInt(request.getParameter("book")));
            bookRequest.setReaderId(Integer.parseInt(request.getParameter("reader")));
            bookRequest.setRequestDate(Helpers.stringToDateTime(request.getParameter("requestDate"), true));
            bookRequest.setReturnDate(Helpers.stringToDateTime(request.getParameter("returnDate"), true));

            if (request.getParameter("edit") == null) {
                bookRequestDao.create(bookRequest, connection);
            } else {
                bookRequestDao.update(bookRequest, connection);
            }

            response.sendRedirect(request.getContextPath() + "/book-request/list.jsp");

        } catch (IOException | NumberFormatException ex) {
            ex.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
