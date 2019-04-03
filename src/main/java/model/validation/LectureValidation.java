package model.validation;

import java.time.LocalDateTime;

public interface LectureValidation {
    boolean startTimeValid(LocalDateTime lectureTime, LocalDateTime conferenceTime);

    boolean titleValid(String title);

    boolean descriptionValid(String description);

    boolean titleEnValid(String title);

    boolean descriptionEnValid(String description);
}
