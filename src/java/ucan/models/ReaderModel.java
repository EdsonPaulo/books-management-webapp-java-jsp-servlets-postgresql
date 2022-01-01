package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class ReaderModel {

    private int readerId, personId;
    private LocalDateTime creationDate;

    public ReaderModel() {
    }
    
    public ReaderModel(int readerId, int personId, LocalDateTime creationDate) {
        this.readerId = readerId;
        this.personId = personId;
        this.creationDate = creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
