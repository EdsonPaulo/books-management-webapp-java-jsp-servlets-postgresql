package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class ProvinceModel {

    private int provinceId, countryId;
    private String name;
    private LocalDate creationDate;

    public ProvinceModel() {
    }

    public ProvinceModel(int provinceId, int countryId, String name, LocalDate creationDate) {
        this.provinceId = provinceId;
        this.countryId = countryId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
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
