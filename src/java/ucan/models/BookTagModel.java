package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class BookTagModel {

    private int bookTagId;
    private int bookId, tagId;
    private LocalDateTime creationDate;

    public BookTagModel() {
    }

    public BookTagModel(int authorBookId, int bookId, int authorId, LocalDateTime creationDate) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
