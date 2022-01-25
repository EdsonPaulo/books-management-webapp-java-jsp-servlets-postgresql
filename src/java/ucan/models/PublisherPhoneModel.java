package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class PublisherPhoneModel {

    private int publisherPhoneId, publisherId;
    private String phone;
    private LocalDateTime creationDate;

    public PublisherPhoneModel() {
    }

    public PublisherPhoneModel(int personPhoneId, int publisherId, String phone, LocalDateTime creationDate) {
        this.publisherPhoneId = personPhoneId;
        this.publisherId = publisherId;
        this.phone = phone;
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getPublisherPhoneId() {
        return publisherPhoneId;
    }

    public void setPublisherPhoneId(int publisherPhoneId) {
        this.publisherPhoneId = publisherPhoneId;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

}
