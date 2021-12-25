package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class PublisherModel {

    private int publisherId, addressId;
    private String name, phone, email, fax, createdAt;

    public PublisherModel(int publisherId, int addressId, String name, String phone, String email, String fax, String createdAt) {
        this.publisherId = publisherId;
        this.addressId = addressId;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.fax = fax;
        this.createdAt = createdAt;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
 
}
