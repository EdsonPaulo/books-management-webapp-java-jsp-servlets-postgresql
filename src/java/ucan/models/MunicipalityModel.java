package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class MunicipalityModel {

    private int municipalityId, provinceId;
    private String name;
    private LocalDateTime creationDate;

    public MunicipalityModel() {
    }

    public MunicipalityModel(int municipalityId, int provinceId, String name, LocalDateTime creationDate) {
        this.municipalityId = municipalityId;
        this.provinceId = provinceId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
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
