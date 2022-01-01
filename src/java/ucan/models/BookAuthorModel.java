package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class BookAuthorModel {

    private int bookAuthorId;
    private int bookId, authorId;
    private LocalDateTime creationDate;

    public BookAuthorModel() {
    }

    public BookAuthorModel(int authorBookId, int bookId, int authorId, LocalDateTime creationDate) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
