package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class BookLocationModel {

    private int locationId;
    private int numCorredor, numArmario, numPrateleira;
    private LocalDate creationDate;

    public BookLocationModel() {
    }

    public BookLocationModel(int locationId, int numCorredor, int numArmario, int numPrateleira, LocalDate creationDate) {
        this.locationId = locationId;
        this.numCorredor = numCorredor;
        this.numArmario = numArmario;
        this.numPrateleira = numPrateleira;
        this.creationDate = creationDate;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getNumCorredor() {
        return numCorredor;
    }

    public void setNumCorredor(int numCorredor) {
        this.numCorredor = numCorredor;
    }

    public int getNumArmario() {
        return numArmario;
    }

    public void setNumArmario(int numArmario) {
        this.numArmario = numArmario;
    }

    public int getNumPrateleira() {
        return numPrateleira;
    }

    public void setNumPrateleira(int numPrateleira) {
        this.numPrateleira = numPrateleira;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
