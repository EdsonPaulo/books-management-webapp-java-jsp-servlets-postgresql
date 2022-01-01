package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class GenderModel {

    private int genderId;
    private String name;
    private LocalDateTime creationDate;

    public GenderModel() {
    }

    public GenderModel( String name ) {
        this.name = name;
    }

     public GenderModel(int genderId, String name, LocalDateTime creationDate) {
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "GenderModel{" + "genderId=" + genderId + ", name=" + name + ", creationDate=" + creationDate + '}';
    }

}
