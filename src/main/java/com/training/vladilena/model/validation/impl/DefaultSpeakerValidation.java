package com.training.vladilena.model.validation.impl;

import com.training.vladilena.model.validation.SpeakerValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class DefaultSpeakerValidation implements SpeakerValidation {
    private static volatile SpeakerValidation speakerValidation;
    private static final Logger LOGGER = LogManager.getLogger(DefaultSpeakerValidation.class);

    private DefaultSpeakerValidation() {
    }

    public static SpeakerValidation getInstance() {
        SpeakerValidation localInstance = speakerValidation;
        if (localInstance == null) {
            synchronized (DefaultUserValidation.class) {
                localInstance = speakerValidation;
                if (localInstance == null) {
                    speakerValidation = new DefaultSpeakerValidation();
                    LOGGER.debug("Create first DefaultSpeakerValidation instance");
                }
            }
        }
        LOGGER.debug("Return DefaultSpeakerValidation instance");
        return speakerValidation;
    }

    @Override
    public boolean ratingValid(double rating) {
        return rating > 0 && rating < 20;
    }
}


