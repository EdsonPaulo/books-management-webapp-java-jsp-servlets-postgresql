package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookModel;
import ucan.utils.DBConnection;

public class BookDAO {
    private DBConnection connection;

    public BookDAO() {

    }

    public void create(BookModel book) {
        String sql = "INSERT INTO livro(nome, isbn, num_paginas, num_edicao, ano_lancamento, fk_estado, fk_classificacao, fk_localizacao, fk_categoria) values(?,?,?,?,?,?,?,?,?)";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getNumPages());
            ps.setInt(4, book.getEditionNum());
            ps.setInt(5, book.getReleaseYear());
            ps.setInt(6, book.getStatusId());
            ps.setInt(7, book.getClassificationId());
            ps.setInt(8, book.getLocationId());
            ps.setInt(9, book.getCategoryId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public void update(BookModel book) {
        String sql = "UPDATE livro SET nome  = ?, isbn = ?, num_paginas = ?, num_edicao = ?, ano_lancamento = ?, fk_estado = ?, fk_classificacao = ?, fk_localizacao = ?, fk_categoria = ? WHERE pk_livro = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getNumPages());
            ps.setInt(4, book.getEditionNum());
            ps.setInt(5, book.getReleaseYear());
            ps.setInt(6, book.getStatusId());
            ps.setInt(7, book.getClassificationId());
            ps.setInt(8, book.getLocationId());
            ps.setInt(9, book.getCategoryId());
            ps.setInt(10, book.getBookId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public void delete(int livroId) {
        String sql = "DELETE FROM livro WHERE pk_livro = ?";
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, livroId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public List<BookModel> getAll() {
        String sql = "SELECT * FROM livro";
        List<BookModel> bookList = new ArrayList<>();
        try {
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookModel book = new BookModel();
                book.setBookId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setIsbn(resultSet.getString(3));
                book.setNumPages(resultSet.getInt(4));
                book.setEditionNum(resultSet.getInt(5));
                book.setReleaseYear(resultSet.getInt(6));
                book.setStatusId(resultSet.getInt(7));
                book.setClassificationId(resultSet.getInt(8));
                book.setLocationId(resultSet.getInt(9));
                book.setCategoryId(resultSet.getInt(10));
                book.setCreationDate(resultSet.getTimestamp(11).toLocalDateTime());

                bookList.add(book);
            }
            ps.close();
            resultSet.close();

            return bookList;

        } catch (SQLException e) {
            return null;
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
    }

    public BookModel getBookById(int bookId) {

        String sql = "SELECT * FROM livro WHERE pk_livro = ?";

        try {
            connection = new DBConnection();
            BookModel book = new BookModel();
            connection = new DBConnection();
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                book.setBookId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setIsbn(resultSet.getString(3));
                book.setNumPages(resultSet.getInt(4));
                book.setEditionNum(resultSet.getInt(5));
                book.setReleaseYear(resultSet.getInt(6));
                book.setStatusId(resultSet.getInt(7));
                book.setClassificationId(resultSet.getInt(8));
                book.setLocationId(resultSet.getInt(9));
                book.setCategoryId(resultSet.getInt(10));
                book.setCreationDate(resultSet.getTimestamp(11).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return book;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.closeConnection();
            }
        }
        return null;
    }
}
