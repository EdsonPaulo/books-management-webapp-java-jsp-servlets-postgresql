package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class ReaderModel {

    private int readerId, personId;
    private LocalDate creationDate;

    public ReaderModel() {
    }
    
    public ReaderModel(int readerId, int personId, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
