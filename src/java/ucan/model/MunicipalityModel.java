package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class MunicipalityModel {

    private int municipalityId, provinceId;
    private String name, createdAt;

    public MunicipalityModel(int municipalityId, int provinceId, String name, String createdAt) {
        this.municipalityId = municipalityId;
        this.provinceId = provinceId;
        this.name = name;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
