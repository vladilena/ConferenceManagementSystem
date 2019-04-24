package com.training.vladilena.model.validation.impl;

import com.training.vladilena.model.validation.ConferenceValidation;
import com.training.vladilena.util.RegexManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultConferenceValidation implements ConferenceValidation {
    private static volatile ConferenceValidation conferenceValidation;
    private static final Logger LOGGER = LogManager.getLogger(DefaultConferenceValidation.class);

    private DefaultConferenceValidation() {
    }

    /**
     * Always return same {@link DefaultConferenceValidation} instance
     *
     * @return always return same {@link DefaultConferenceValidation} instance
     */
    public static ConferenceValidation getInstance() {
        ConferenceValidation localInstance = conferenceValidation;
        if (localInstance == null) {
            synchronized (DefaultUserValidation.class) {
                localInstance = conferenceValidation;
                if (localInstance == null) {
                    conferenceValidation = new DefaultConferenceValidation();
                    LOGGER.debug(" Create first instance of DefaultConferenceValidation");
                }
            }
        }
        LOGGER.debug("Return DefaultConferenceValidation instance");
        return conferenceValidation;
    }

    @Override
    public boolean titleValid(String title) {
        return Pattern.matches(RegexManager.getProperty("title.ukr"), title);
    }

    @Override
    public boolean titleEnValid(String title) {
        return Pattern.matches(RegexManager.getProperty("title.en"), title);
    }

    @Override
    public boolean descriptionValid(String description) {
        if (description == null) {
            return false;
        }
        Pattern p = Pattern.compile(RegexManager.getProperty("description.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(description);
        return m.matches();
    }

    @Override
    public boolean descriptionEnValid(String description) {
        if (description == null) {
            return false;
        }
        Pattern p = Pattern.compile(RegexManager.getProperty("description.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(description);
        return m.matches();
    }

    @Override
    public boolean dateTimeValid(LocalDateTime dateTime) {
        return dateTime.isAfter(LocalDateTime.now());
    }

    @Override
    public boolean placeValid(String place) {
        if (place == null) {
            return false;
        }
        Pattern p = Pattern.compile(RegexManager.getProperty("place.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(place);
        return m.matches();
    }

    @Override
    public boolean placeEnValid(String place) {
        if (place == null) {
            return false;
        }
        Pattern p = Pattern.compile(RegexManager.getProperty("place.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(place);
        return m.matches();
    }

    @Override
    public boolean lecturesCapacityValid(int lecturesCapacity) {
        return lecturesCapacity > 0;
    }

    @Override
    public boolean placeCapacity(int placeCapacity) {
        return placeCapacity > 0;
    }
}


