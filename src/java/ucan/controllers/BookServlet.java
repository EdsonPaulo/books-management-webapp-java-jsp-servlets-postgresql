package ucan.controllers;

import ucan.utils.Helpers;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ucan.conection.DBConnection;
import ucan.dao.BookAuthorDAO;
import ucan.dao.BookDAO;
import ucan.dao.BookTagDAO;
import ucan.models.BookAuthorModel;
import ucan.models.BookModel;
import ucan.models.BookTagModel;

/**
 *
 * @author edsonpaulo
 */
@WebServlet(name = "BookServlet", urlPatterns = {"/book-servlet"})
public class BookServlet extends HttpServlet {

    private DBConnection connection;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            connection = new DBConnection();

            if (request.getParameter("action") != null) {
                BookDAO bookDao = new BookDAO();
                int id = Integer.parseInt(request.getParameter("id"));

                if (request.getParameter("action").equals("delete")) {
                    bookDao.delete(id, connection);
                    response.sendRedirect(request.getContextPath() + "/book/list.jsp");
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
            BookModel book = new BookModel();
            BookDAO bookDao = new BookDAO();

            book.setName(request.getParameter("name"));
            book.setIsbn(request.getParameter("isbn"));
            book.setNumPages(Integer.parseInt(request.getParameter("numPages")));
            book.setEditionNum(Integer.parseInt(request.getParameter("editionNum")));
            book.setReleaseYear(Integer.parseInt(request.getParameter("releaseYear")));
            book.setCategoryId(Integer.parseInt(request.getParameter("category")));
            book.setStatusId(Integer.parseInt(request.getParameter("status")));
            book.setLocationId(Integer.parseInt(request.getParameter("location")));
            book.setPublisherId(Integer.parseInt(request.getParameter("publisher")));

            bookDao.create(book, connection);
            int bookId = Helpers.getIdOfLastRow("livro", connection);

            /**
             * BOOK AUTHORS
             */
            BookAuthorModel bookAuthor = new BookAuthorModel();
            BookAuthorDAO bookAuthorDao = new BookAuthorDAO();
            String[] authors = request.getParameterValues("authors");

            bookAuthor.setBookId(bookId);

            for (String author : authors) {
                bookAuthor.setAuthorId(Integer.parseInt(author));
                bookAuthorDao.create(bookAuthor, connection);
            }

            /**
             *
             * BOOK TAGS
             */
            BookTagModel bookTag = new BookTagModel();
            BookTagDAO bookTagDao = new BookTagDAO();
            String[] tags = request.getParameterValues("tags");

            bookTag.setBookId(bookId);

            for (String tag : tags) {
                bookTag.setTagId(Integer.parseInt(tag));
                bookTagDao.create(bookTag, connection);
            }

            if (request.getParameter("edit") == null) {

            } else {

            }

            response.sendRedirect(request.getContextPath() + "/book/list.jsp");

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
