package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class TagModel {

    private int tagId;
    private String name;
    private LocalDateTime creationDate;

    public TagModel() {
    }

    public TagModel(int tagId, String name, LocalDateTime creationDate) {
        this.tagId = tagId;
        this.name = name;
        this.creationDate = creationDate;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

}
