package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import ucan.models.BookModel;
import ucan.utils.DBConnection;

public class BookDAO {

    public BookDAO() {

    }

    public static void create(BookModel book, DBConnection connection) {
        String sql = "INSERT INTO livro(nome, isbn, qtd_paginas, qtg_copias, num_edicao, ano_lancamento, fk_estado, fk_classificacao, fk_localizacao, fk_categoria) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getNumPages());
            ps.setInt(4, book.getNumCopy());
            ps.setInt(5, book.getNumEdition());
            ps.setInt(6, book.getReleaseYear());
            ps.setInt(7, book.getStatusId());
            ps.setInt(8, book.getClassificationId());
            ps.setInt(9, book.getLocationId());
            ps.setInt(10, book.getCategoryId());

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

    public static void update(BookModel book, DBConnection connection) {
        String sql = "UPDATE livro SET nome  = ?, isbn = ?, qtd_paginas = ?, qtg_copias = ?, num_edicao = ?, ano_lancamento = ?, fk_estado = ?, fk_classificacao = ?, fk_localizacao = ?, fk_categoria = ? WHERE pk_livro = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getNumPages());
            ps.setInt(4, book.getNumCopy());
            ps.setInt(5, book.getNumEdition());
            ps.setInt(6, book.getReleaseYear());
            ps.setInt(7, book.getStatusId());
            ps.setInt(8, book.getClassificationId());
            ps.setInt(9, book.getLocationId());
            ps.setInt(10, book.getCategoryId());
            ps.setInt(11, book.getBookId());

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

    public static void delete(int livroId, DBConnection connection) {
        String sql = "DELETE FROM livro WHERE pk_livro = ?";
        try {
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

    public static List<BookModel> getAll(DBConnection connection) {
        String sql = "SELECT * FROM livro";

        List<BookModel> bookList = new ArrayList<>();

        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                BookModel book = new BookModel();
                book.setBookId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setIsbn(resultSet.getString(3));
                book.setNumPages(resultSet.getInt(4));
                book.setNumCopy(resultSet.getInt(5));
                book.setNumEdition(resultSet.getInt(6));
                book.setReleaseYear(resultSet.getInt(7));
                book.setStatusId(resultSet.getInt(8));
                book.setClassificationId(resultSet.getInt(9));
                book.setLocationId(resultSet.getInt(10));
                book.setCategoryId(resultSet.getInt(11));
                book.setCreationDate(resultSet.getDate(12).toLocalDate());

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

    public static BookModel getBookById(int bookId, DBConnection connection) {

        String sql = "SELECT * FROM livro WHERE pk_livro = ?";

        try {
            BookModel book = new BookModel();

            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, bookId);

            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                book.setBookId(resultSet.getInt(1));
                book.setName(resultSet.getString(2));
                book.setIsbn(resultSet.getString(3));
                book.setNumPages(resultSet.getInt(4));
                book.setNumCopy(resultSet.getInt(5));
                book.setNumEdition(resultSet.getInt(6));
                book.setReleaseYear(resultSet.getInt(7));
                book.setStatusId(resultSet.getInt(8));
                book.setClassificationId(resultSet.getInt(9));
                book.setLocationId(resultSet.getInt(10));
                book.setCategoryId(resultSet.getInt(11));
                book.setCreationDate(resultSet.getDate(12).toLocalDate());
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