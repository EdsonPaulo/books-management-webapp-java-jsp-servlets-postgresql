package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class AuthorModel {

    private int authorId, personId;
    private LocalDateTime creationDate;

    public AuthorModel() {
    }

    public AuthorModel(int readerId, int personId, LocalDateTime creationDate) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
