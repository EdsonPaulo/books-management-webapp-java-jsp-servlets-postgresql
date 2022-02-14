package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class BookLocationModel {

    private int locationId, rackNum;
    private String name;
    private LocalDateTime creationDate;

    public BookLocationModel() {
    }

    public BookLocationModel(int locationId, String name, int rackNum, LocalDateTime creationDate) {
        this.locationId = locationId;
        this.name = name;
        this.rackNum = rackNum;
        this.creationDate = creationDate;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getRackNum() {
        return rackNum;
    }

    public void setRackNum(int rackNum) {
        this.rackNum = rackNum;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
