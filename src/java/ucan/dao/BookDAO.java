package ucan.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import ucan.models.BookModel;
import ucan.conection.DBConnection;

public class BookDAO {

    public BookDAO() {

    }

    public void create(BookModel book, DBConnection connection) {
        String sql = "INSERT INTO livro(nome, isbn, num_paginas, num_edicao, ano_lancamento, "
                + "fk_editora, fk_estado, fk_localizacao, fk_categoria) values(?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getNumPages());
            ps.setInt(4, book.getEditionNum());
            ps.setInt(5, book.getReleaseYear());
            ps.setInt(6, book.getPublisherId());
            ps.setInt(7, book.getStatusId());
            ps.setInt(8, book.getLocationId());
            ps.setInt(9, book.getCategoryId());

            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(BookModel book, DBConnection connection) {
        String sql = "UPDATE livro SET nome  = ?, isbn = ?, num_paginas = ?, num_edicao = ?, ano_lancamento = ?, "
                + "fk_estado = ?, fk_localizacao = ?, fk_categoria = ? WHERE pk_livro = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);

            ps.setString(1, book.getName());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getNumPages());
            ps.setInt(4, book.getEditionNum());
            ps.setInt(5, book.getReleaseYear());
            ps.setInt(6, book.getPublisherId());
            ps.setInt(7, book.getStatusId());
            ps.setInt(8, book.getLocationId());
            ps.setInt(9, book.getCategoryId());
            ps.setInt(10, book.getBookId());
            ps.executeUpdate();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int livroId, DBConnection connection) {
        String sql = "DELETE FROM livro WHERE pk_livro = ?";
        try {
            PreparedStatement ps = connection.getConnection().prepareStatement(sql);
            ps.setInt(1, livroId);

            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<BookModel> getAll(DBConnection connection) {
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
                book.setEditionNum(resultSet.getInt(5));
                book.setReleaseYear(resultSet.getInt(6));
                book.setPublisherId(resultSet.getInt(7));
                book.setStatusId(resultSet.getInt(8));
                book.setLocationId(resultSet.getInt(9));
                book.setCategoryId(resultSet.getInt(10));
                book.setCreationDate(resultSet.getTimestamp(11).toLocalDateTime());

                bookList.add(book);
            }
            ps.close();
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bookList;
    }

    public BookModel getBookById(int bookId, DBConnection connection) {

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
                book.setEditionNum(resultSet.getInt(5));
                book.setReleaseYear(resultSet.getInt(6));
                book.setPublisherId(resultSet.getInt(7));
                book.setStatusId(resultSet.getInt(8));
                book.setLocationId(resultSet.getInt(9));
                book.setCategoryId(resultSet.getInt(10));
                book.setCreationDate(resultSet.getTimestamp(11).toLocalDateTime());
            }

            ps.close();
            resultSet.close();
            return book;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    public Vector<String> getBookAuthors(int bookId, DBConnection connection) {
        Vector<String> authors = new Vector<>();
        String sql = "SELECT pessoa.nome FROM livro_autor"
                + " INNER JOIN autor ON livro_autor.fk_autor = autor.pk_autor "
                + " INNER JOIN livro ON livro_autor.fk_livro = livro.pk_livro "
                + " INNER JOIN pessoa ON autor.fk_pessoa = pessoa.pk_pessoa WHERE livro_autor.fk_livro = " + bookId
                + ";";
        try {
            ResultSet resultSet = connection.getConnection().prepareStatement(sql).executeQuery();

            while (resultSet.next()) {
                authors.add(resultSet.getString(1));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return authors;
    }

    public Vector<String> getBookTags(int bookId, DBConnection connection) {
        Vector<String> tags = new Vector<>();
        String sql = "SELECT descritores.nome FROM livro_descritores"
                + " INNER JOIN descritores ON livro_descritores.fk_descritores = descritores.pk_descritores "
                + " INNER JOIN livro ON livro_descritores.fk_livro = livro.pk_livro WHERE livro_descritores.fk_livro = " + bookId
                + ";";
        try {
            ResultSet resultSet = connection.getConnection().prepareStatement(sql).executeQuery();

            while (resultSet.next()) {
                tags.add(resultSet.getString(1));
            }
            resultSet.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tags;
    }
}
