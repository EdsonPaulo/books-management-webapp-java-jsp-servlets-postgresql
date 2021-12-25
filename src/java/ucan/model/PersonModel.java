package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class PersonModel {

    private int personId, genderId, addressId;
    private String name, surname, phone, birthdate, email, createdAt;

    public PersonModel(int personId, int addressId, int genderId, String name, String surname, String phone, String birthdate, String email, String createdAt) {
        this.personId = personId;
        this.addressId = addressId;
        this.genderId = genderId;
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.birthdate = birthdate;
        this.email = email;
        this.createdAt = createdAt;
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

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
