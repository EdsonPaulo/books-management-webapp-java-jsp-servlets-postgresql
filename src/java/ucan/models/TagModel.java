package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class TagModel {

    private int tagId;
    private String name;
    private LocalDate creationDate;

    public TagModel() {
    }

    public TagModel(int tagId, String name, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
