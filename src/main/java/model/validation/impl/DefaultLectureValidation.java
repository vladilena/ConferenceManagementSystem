package model.validation.impl;

import model.util.RegexManager;
import model.validation.LectureValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DefaultLectureValidation implements LectureValidation {
    private static volatile LectureValidation lectureValidation;
    private static final Logger LOGGER = LogManager.getLogger(DefaultLectureValidation.class);



    private DefaultLectureValidation() {
    }

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
    public boolean startTimeValid(String startTime) {
        return Pattern.matches(RegexManager.getProperty("startTime"), startTime);
    }

    @Override
    public boolean titleValid(String title) {
        if (title == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("title.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(title);
        return m.matches();
    }
    @Override
    public boolean titleEnValid(String title) {
        if (title == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("title.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(title);
        return m.matches();
    }
    @Override
    public boolean descriptionValid(String description) {
        if (description == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("description.ukr"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(description);
        return m.matches();
    }
    @Override
    public boolean descriptionEnValid(String description) {
        if (description == null){return false;}
        Pattern p = Pattern.compile(RegexManager.getProperty("description.en"), Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
        Matcher m = p.matcher(description);
        return m.matches();
    }

}


