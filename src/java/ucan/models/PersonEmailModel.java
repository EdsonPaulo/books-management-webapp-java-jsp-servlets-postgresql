package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class PersonEmailModel {

    private int personEmailId, personId;
    private String email;
    private LocalDateTime creationDate;

    public PersonEmailModel() {
    }

    public PersonEmailModel(int personEmailId, int personId, String email, LocalDateTime creationDate) {
        this.personEmailId = personEmailId;
        this.personId = personId;
        this.email = email;
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPersonEmailId() {
        return personEmailId;
    }

    public void setPersonEmailId(int personEmailId) {
        this.personEmailId = personEmailId;
    }

}
