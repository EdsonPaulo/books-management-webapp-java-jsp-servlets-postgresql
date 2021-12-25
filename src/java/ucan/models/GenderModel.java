package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class GenderModel {

    private int genderId;
    private String name;
    private LocalDate creationDate;

    public GenderModel() {
    }

    public GenderModel(int genderId, String name, LocalDate creationDate) {
        this.genderId = genderId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getGenderId() {
        return genderId;
    }

    public void setGenderId(int genderId) {
        this.genderId = genderId;
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
