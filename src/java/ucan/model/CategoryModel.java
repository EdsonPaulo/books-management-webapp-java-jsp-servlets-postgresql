package ucan.model;

/**
 *
 * @author edsonpaulo
 */
public class CategoryModel {

    private int categoryId;
    private String name, createdAt;

    public CategoryModel(int categoryId, String name, String createdAt) {
        this.categoryId = categoryId;
        this.name = name;
        this.createdAt = createdAt;
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

}
