package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class PersonPhoneModel {

    private int personPhoneId, personId;
    private String phone;
    private LocalDateTime creationDate;

    public PersonPhoneModel() {
    }

    public PersonPhoneModel(int personPhoneId, int personId, String phone, LocalDateTime creationDate) {
        this.personPhoneId = personPhoneId;
        this.personId = personId;
        this.phone = phone;
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getPersonPhoneId() {
        return personPhoneId;
    }

    public void setPersonPhoneId(int personPhoneId) {
        this.personPhoneId = personPhoneId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
