package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class StatusModel {

    private int statusId;
    private String name, createdAt;

    public StatusModel(int statusId, String name, String createdAt) {
        this.statusId = statusId;
        this.name = name;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
