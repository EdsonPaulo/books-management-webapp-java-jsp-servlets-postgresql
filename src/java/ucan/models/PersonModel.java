package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class PersonModel {

    private int personId, genderId, addressId;
    private String name, surname, phone, email;
    private LocalDateTime birthDate, creationDate;

    public PersonModel() {
    }

    public PersonModel(int personId, int addressId, int genderId, String name, String surname, String phone, LocalDateTime birthDate, String email, LocalDateTime creationDate) {
        this.personId = personId;
        this.addressId = addressId;
        this.genderId = genderId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.birthDate = birthDate;
        this.email = email;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDateTime birthdate) {
        this.birthDate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }
}
