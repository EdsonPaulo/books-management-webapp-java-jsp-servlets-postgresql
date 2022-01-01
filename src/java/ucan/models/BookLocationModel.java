package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class BookLocationModel {

    private int locationId;
    private int hallwayNum, cabinetNum, rackNum;
    private LocalDateTime creationDate;

    public BookLocationModel() {
    }

    public BookLocationModel(int locationId, int numCorredor, int numArmario, int numPrateleira, LocalDateTime creationDate) {
        this.locationId = locationId;
        this.hallwayNum = numCorredor;
        this.cabinetNum = numArmario;
        this.rackNum = numPrateleira;
        this.creationDate = creationDate;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getHallwayNum() {
        return hallwayNum;
    }

    public void setHallwayNum(int hallwayNum) {
        this.hallwayNum = hallwayNum;
    }

    public int getCabinetNum() {
        return cabinetNum;
    }

    public void setCabinetNum(int cabinetNum) {
        this.cabinetNum = cabinetNum;
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

}
