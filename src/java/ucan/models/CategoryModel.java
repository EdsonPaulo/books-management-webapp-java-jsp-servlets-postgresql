package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class CategoryModel {

    private int categoryId;
    private String name;
    private LocalDateTime creationDate;

    public CategoryModel() {
    }

    public CategoryModel(int categoryId, String name, LocalDateTime creationDate) {
        this.categoryId = categoryId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
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
