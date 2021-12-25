package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class CommuneModel {

    private int communeId, municipalityId;
    private String name;
    private LocalDate creationDate;

    public CommuneModel() {
    }

    public CommuneModel(int communeId, int municipalityId, String name, LocalDate creationDate) {
        this.communeId = communeId;
        this.municipalityId = municipalityId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
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
