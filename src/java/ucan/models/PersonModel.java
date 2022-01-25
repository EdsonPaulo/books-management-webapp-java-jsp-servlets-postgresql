package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class PersonModel {

    private int personId, genderId, addressId;
    private String name, surname, bi;
    private LocalDateTime birthDate, creationDate;

    public PersonModel() {
    }

    public PersonModel(int personId, int addressId, int genderId, String name, String surname, String bi, LocalDateTime birthDate, LocalDateTime creationDate) {
        this.personId = personId;
        this.addressId = addressId;
        this.genderId = genderId;
        this.name = name;
        this.surname = surname;
        this.bi = bi;
        this.birthDate = birthDate;
        this.creationDate = creationDate;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthdate) {
        this.birthDate = birthdate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "PersonModel{" + "personId=" + personId + ", genderId=" + genderId + ", addressId=" + addressId + ", name=" + name + ", surname=" + surname + ", bi=" + bi + ", birthDate=" + birthDate + ", creationDate=" + creationDate + '}';
    }

}
