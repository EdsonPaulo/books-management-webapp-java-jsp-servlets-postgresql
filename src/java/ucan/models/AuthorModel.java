package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class AuthorModel {

    private int authorId, personId;
    private LocalDate creationDate;

    public AuthorModel() {
    }

    public AuthorModel(int readerId, int personId, LocalDate creationDate) {
        this.authorId = readerId;
        this.personId = personId;
        this.creationDate = creationDate;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
