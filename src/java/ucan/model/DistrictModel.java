package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class DistrictModel {

    private int communeId, districtId;
    private String name, createdAt;

    public DistrictModel(int districtId, int communeId, String name, String createdAt) {
        this.districtId = districtId;
        this.communeId = communeId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        this.districtId = districtId;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
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
