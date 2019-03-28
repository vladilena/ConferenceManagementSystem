package model.validation;

public interface LectureValidation {
    boolean startTimeValid(String startTime);

    boolean titleValid(String title);

    boolean descriptionValid(String description);

    boolean titleEnValid(String title);

    boolean descriptionEnValid(String description);
}
