package ucan.models;

import java.time.LocalDate;

/**
 *
 * @author edsonpaulo
 */
public class ClassificationModel {

    private int classificationId;
    private String name;
    private LocalDate creationDate;

    public ClassificationModel() {
    }

    public ClassificationModel(int classificationId, String name, LocalDate creationDate) {
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

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

}
