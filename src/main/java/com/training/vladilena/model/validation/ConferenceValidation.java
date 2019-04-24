package com.training.vladilena.model.validation;

import com.training.vladilena.model.entity.Conference;

import java.time.LocalDateTime;

/**
 * The {@code ConferenceValidation} is a interface which provide method to validate {@link Conference}
 *
 * @author Vladlena ushakova
 */
public interface ConferenceValidation {

    boolean titleValid(String title);

    boolean titleEnValid(String title);

    boolean descriptionValid(String description);

    boolean descriptionEnValid(String description);

    boolean dateTimeValid(LocalDateTime dateTime);

    boolean placeValid(String place);

    boolean placeEnValid(String place);

    boolean lecturesCapacityValid(int lecturesCapacity);

    boolean placeCapacity(int placeCapacity);
}
