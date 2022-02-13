package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class PublisherModel {

    private int publisherId, addressId;
    private String name, nif, fax;
    private LocalDateTime creationDate;

    public PublisherModel() {
    }

    public PublisherModel(int publisherId, int addressId, String name, String nif, String fax, LocalDateTime creationDate) {
        this.publisherId = publisherId;
        this.addressId = addressId;
        this.name = name;
        this.nif = nif;
        this.fax = fax;
        this.creationDate = creationDate;
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

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        this.nif = nif;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
