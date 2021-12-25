package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class TagModel {

    private int tagId;
    private String name, createdAt;

    public TagModel(int tagId, String name, String createdAt) {
        this.tagId = tagId;
        this.name = name;
        this.createdAt = createdAt;
    }

    public int getTagId() {
        return tagId;
    }

    public void setTagId(int tagId) {
        this.tagId = tagId;
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
