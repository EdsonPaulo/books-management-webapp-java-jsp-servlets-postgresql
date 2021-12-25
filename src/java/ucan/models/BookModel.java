package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class BookModel {
    
    private int bookId;
    private int pagesNum, copyNum, releaseYear;
    private String isbn, nome;
    private int categoryId, classificationId, locationId, statusId;
    private LocalDate creationDate;

    public BookModel() {
    }
      
    public BookModel(int bookId, int pagesNum, int copyNum, int releaseYear, String isbn, String nome, LocalDate creationDate) {
        this.bookId = bookId;
        this.pagesNum = pagesNum;
        this.copyNum = copyNum;
        this.releaseYear = releaseYear;
        this.isbn = isbn;
        this.nome = nome;
        this.creationDate = creationDate;
    }
 
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getPagesNum() {
        return pagesNum;
    }

    public void setPagesNum(int pagesNum) {
        this.pagesNum = pagesNum;
    }

    public int getCopyNum() {
        return copyNum;
    }

    public void setCopyNum(int copyNum) {
        this.copyNum = copyNum;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }
 
  
    
    
    
}
