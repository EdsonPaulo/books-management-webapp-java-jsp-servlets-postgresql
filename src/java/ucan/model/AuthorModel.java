package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class AuthorModel {

    private int authorId, personId;
    private String createdAt;

    public AuthorModel(int readerId, int personId, String createdAt) {
        this.authorId = readerId;
        this.personId = personId;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
