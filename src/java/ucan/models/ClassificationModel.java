package ucan.models;

import java.time.LocalDateTime;

/**
 *
 * @author edsonpaulo
 */
public class ClassificationModel {

    private int classificationId;
    private String name;
    private LocalDateTime creationDate;

    public ClassificationModel() {
    }

    public ClassificationModel(int classificationId, String name, LocalDateTime creationDate) {
        this.classificationId = classificationId;
        this.name = name;
        this.creationDate = creationDate;
    }

    public int getClassificationId() {
        return classificationId;
    }

    public void setClassificationId(int classificationId) {
        this.classificationId = classificationId;
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
