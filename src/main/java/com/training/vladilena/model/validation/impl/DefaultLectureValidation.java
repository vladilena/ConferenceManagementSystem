package com.training.vladilena.model.validation.impl;

import com.training.vladilena.model.validation.LectureValidation;
import com.training.vladilena.util.RegexManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultLectureValidation implements LectureValidation {
    private static volatile LectureValidation lectureValidation;
    private static final Logger LOGGER = LogManager.getLogger(DefaultLectureValidation.class);

    private DefaultLectureValidation() {
    }
    /**
     * Always return same {@link DefaultLectureValidation} instance
     *
     * @return always return same {@link DefaultLectureValidation} instance
     */
    public static LectureValidation getInstance(){
        LectureValidation localInstance = lectureValidation;
        if(localInstance == null) {
            synchronized (DefaultUserValidation.class) {
                localInstance = lectureValidation;
                if(localInstance == null) {
                    lectureValidation = new DefaultLectureValidation();
                     LOGGER.debug("Create first DefaultLectureValidation instance");
                }
            }
        }
         LOGGER.debug("Return DefaultLectureValidation instance");
        return lectureValidation;
    }

    @Override
    public boolean startTimeValid(LocalDateTime lectureTime, LocalDateTime conferenceTime) {
        return lectureTime.getDayOfYear()==conferenceTime.getDayOfYear();
    }

    @Override
    public boolean titleValid(String title) {
        if (title == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("lect.title.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(title);
        return m.matches();
    }
    @Override
    public boolean titleEnValid(String title) {
        if (title == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("lect.title.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(title);
        return m.matches();
    }
    @Override
    public boolean descriptionValid(String description) {
        if (description == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("lect.description.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(description);
        return m.matches();
    }
    @Override
    public boolean descriptionEnValid(String description) {
        if (description == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("lect.description.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(description);
        return m.matches();
    }
}


