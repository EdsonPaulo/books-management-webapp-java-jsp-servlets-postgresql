package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class CountryModel {

    private int countryId;
    private String name;
    private LocalDate creationDate;

    public CountryModel() {
    }

    public CountryModel(int countryId, String name, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
