package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class BookPublisherModel {

    private int bookPublisherId;
    private int bookId, publisherId;
    private LocalDate creationDate;

    public BookPublisherModel() {
    }

    public BookPublisherModel(int publisherBookId, int bookId, int publisherId, LocalDate creationDate) {
        this.bookPublisherId = publisherBookId;
        this.bookId = bookId;
        this.publisherId = publisherId;
        this.creationDate = creationDate;
    }

    public int getBookPublisherId() {
        return bookPublisherId;
    }

    public void setBookPublisherId(int bookPublisherId) {
        this.bookPublisherId = bookPublisherId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
}
