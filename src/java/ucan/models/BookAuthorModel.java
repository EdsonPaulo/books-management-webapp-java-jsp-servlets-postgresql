package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class BookAuthorModel {

    private int bookAuthorId;
    private int bookId, authorId;
    private LocalDate creationDate;

    public BookAuthorModel() {
    }

    public BookAuthorModel(int authorBookId, int bookId, int authorId, LocalDate creationDate) {
        this.bookAuthorId = authorBookId;
        this.bookId = bookId;
        this.authorId = authorId;
        this.creationDate = creationDate;
    }

    public int getBookAuthorId() {
        return bookAuthorId;
    }

    public void setBookAuthorId(int bookAuthorId) {
        this.bookAuthorId = bookAuthorId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
