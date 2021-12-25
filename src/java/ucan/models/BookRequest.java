package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class BookRequest {

    private int requestId, bookId, readerId;
    private LocalDate requestDate, returnDate, creationDate;

    public BookRequest() {
    }

    public BookRequest(int requestId, int bookId, int readerId, LocalDate requestDate, LocalDate returnDate, LocalDate creationDate) {
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

    public LocalDate getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate = requestDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
