package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class CategoryModel {

    private int categoryId;
    private String name;
    private LocalDate creationDate;

    public CategoryModel() {
    }

    public CategoryModel(int categoryId, String name, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
