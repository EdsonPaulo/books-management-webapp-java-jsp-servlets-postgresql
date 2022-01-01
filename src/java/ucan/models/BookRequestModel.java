package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class BookRequestModel {

    private int requestId, bookId, readerId;
    private LocalDateTime requestDate, returnDate, creationDate;

    public BookRequestModel() {
    }

    public BookRequestModel(int requestId, int bookId, int readerId, LocalDateTime requestDate, LocalDateTime returnDate, LocalDateTime creationDate) {
        this.requestId = requestId;
        this.bookId = bookId;
        this.readerId = readerId;
        this.requestDate = requestDate;
        this.returnDate = returnDate;
        this.creationDate = creationDate;
    }

    public int getRequestId() {
        return requestId;
    }

    public void setRequestId(int requestId) {
        this.requestId = requestId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
    }

    public LocalDateTime getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDateTime requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDateTime getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDateTime returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
