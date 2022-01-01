package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class StatusModel {

    private int statusId;
    private String name;
    private LocalDateTime creationDate;

    public StatusModel() {
    }

    public StatusModel(int statusId, String name, LocalDateTime creationDate) {
        this.statusId = statusId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
