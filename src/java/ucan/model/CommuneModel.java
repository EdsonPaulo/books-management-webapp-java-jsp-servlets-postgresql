package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class CommuneModel {

    private int communeId, municipalityId;
    private String name, createdAt;

    public CommuneModel(int communeId, int municipalityId, String name, String createdAt) {
        this.communeId = communeId;
        this.municipalityId = municipalityId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getCommuneId() {
        return communeId;
    }

    public void setCommuneId(int communeId) {
        this.communeId = communeId;
    }

    public int getMunicipalityId() {
        return municipalityId;
    }

    public void setMunicipalityId(int municipalityId) {
        this.municipalityId = municipalityId;
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
