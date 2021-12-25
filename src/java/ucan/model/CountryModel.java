package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class CountryModel {

    private int countryId;
    private String name, createdAt;

    public CountryModel(int countryId, String name, String createdAt) {
        this.countryId = countryId;
        this.name = name;
        this.createdAt = createdAt;
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
