package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class ReaderModel {

    private int readerId, personId;
    private String createdAt;

    public ReaderModel(int readerId, int personId, String createdAt) {
        this.readerId = readerId;
        this.personId = personId;
        this.createdAt = createdAt;
    }

    public int getReaderId() {
        return readerId;
    }

    public void setReaderId(int readerId) {
        this.readerId = readerId;
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
