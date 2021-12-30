package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class StatusModel {

    private int statusId;
    private String name;
    private LocalDate creationDate;

    public StatusModel() {
    }

    public StatusModel(int statusId, String name, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
