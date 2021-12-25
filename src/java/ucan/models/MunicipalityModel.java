package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class MunicipalityModel {

    private int municipalityId, provinceId;
    private String name;
    private LocalDate creationDate;

    public MunicipalityModel() {
    }

    public MunicipalityModel(int municipalityId, int provinceId, String name, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
