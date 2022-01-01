package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class CountryModel {

    private int countryId;
    private String name;
    private LocalDateTime creationDate;

    public CountryModel() {
    }

    public CountryModel(int countryId, String name, LocalDateTime creationDate) {
        this.countryId = countryId;
        this.name = name;
        this.creationDate = creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
