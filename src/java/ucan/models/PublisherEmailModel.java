package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class PublisherEmailModel {

    private int publisherEmailId, publisherId;
    private String email;
    private LocalDateTime creationDate;

    public PublisherEmailModel() {
    }

    public PublisherEmailModel(int personEmailId, int personId, String email, LocalDateTime creationDate) {
        this.publisherEmailId = personEmailId;
        this.publisherId = personId;
        this.email = email;
        this.creationDate = creationDate;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getPublisherEmailId() {
        return publisherEmailId;
    }

    public void setPublisherEmailId(int publisherEmailId) {
        this.publisherEmailId = publisherEmailId;
    }

}
