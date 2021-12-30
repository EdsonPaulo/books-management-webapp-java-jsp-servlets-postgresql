package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class BookLocationModel {

    private int locationId;
    private int hallwayNum, cabinetNum, rackNum;
    private LocalDate creationDate;

    public BookLocationModel() {
    }

    public BookLocationModel(int locationId, int numCorredor, int numArmario, int numPrateleira, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
