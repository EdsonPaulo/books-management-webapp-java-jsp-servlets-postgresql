package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class GenderModel {

    private int genderId;
    private String name, createdAt;

    public GenderModel(int genderId, String name, String createdAt) {
        this.genderId = genderId;
        this.name = name;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
