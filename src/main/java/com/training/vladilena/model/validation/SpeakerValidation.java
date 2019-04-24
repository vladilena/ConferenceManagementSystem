package com.training.vladilena.model.validation;

import com.training.vladilena.model.entity.Speaker;

/**
 * The {@code SpeakerValidation} is a interface which provide method to validate {@link Speaker}
 *
 * @author Vladlena ushakova
 */
public interface SpeakerValidation {
    boolean ratingValid(double rating);
}


