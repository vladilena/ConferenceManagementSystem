package com.training.vladilena.model.validation;
import com.training.vladilena.model.entity.Lecture;
import java.time.LocalDateTime;
/**
 * The {@code LectureValidation} is a interface which provide method to validate {@link Lecture}
 *
 * @author Vladlena ushakova
 */
public interface LectureValidation {
    boolean startTimeValid(LocalDateTime lectureTime, LocalDateTime conferenceTime);

    boolean titleValid(String title);

    boolean descriptionValid(String description);

    boolean titleEnValid(String title);

    boolean descriptionEnValid(String description);
}
