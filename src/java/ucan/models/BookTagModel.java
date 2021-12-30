package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class BookTagModel {

    private int bookTagId;
    private int bookId, tagId;
    private LocalDate creationDate;

    public BookTagModel() {
    }

    public BookTagModel(int authorBookId, int bookId, int authorId, LocalDate creationDate) {
        this.bookTagId = authorBookId;
        this.bookId = bookId;
        this.tagId = authorId;
        this.creationDate = creationDate;
    }

    public int getBookTagId() {
        return bookTagId;
    }

    public void setBookTagId(int bookTagId) {
        this.bookTagId = bookTagId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
