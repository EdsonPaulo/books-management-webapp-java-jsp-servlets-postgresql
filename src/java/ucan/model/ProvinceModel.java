package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class ProvinceModel {

    private int provinceId, countryId;
    private String name, createdAt;

    public ProvinceModel(int provinceId, int countryId, String name, String createdAt) {
        this.provinceId = provinceId;
        this.countryId = countryId;
        this.name = name;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
