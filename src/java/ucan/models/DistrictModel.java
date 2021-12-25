package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class DistrictModel {

    private int communeId, districtId;
    private String name;
    private LocalDate creationDate;

    public DistrictModel() {
    }

    public DistrictModel(int districtId, int communeId, String name, LocalDate creationDate) {
        this.districtId = districtId;
        this.communeId = communeId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
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
