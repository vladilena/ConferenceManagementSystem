package com.training.vladilena.controller.command;

import com.training.vladilena.model.dto.InvalidData;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.validation.LectureValidation;
import com.training.vladilena.model.validation.impl.DefaultLectureValidation;
import com.training.vladilena.util.AttributesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code GenerateLecture} interface is used for get {@link Lecture}'s parameters
 * from request and validate them
 *
 * @author Ushakova Vladlena
 */
public interface GenerateLecture {
    Logger LOGGER = LogManager.getLogger(GenerateLecture.class);
    LectureValidation validation = DefaultLectureValidation.getInstance();

    /**
     * The default method which get {@link Lecture} and validate it
     *
     * @param lecture is a Lecture to validate
     * @return {@link InvalidData} instance if lecture is invalid, else return null
     */
    default InvalidData inputChecked(Lecture lecture) {
        InvalidData.Builder builder = InvalidData.newBuilder(AttributesManager.getProperty("invalidData"));
        boolean invalidDataFlag = false;
        if (!validation.titleValid(lecture.getTitle())) {
            LOGGER.debug("Invalid title ukr: " + lecture.getTitle());
            builder.setInvalidTitleAttr();
            invalidDataFlag = true;
        }
        if (!validation.titleEnValid(lecture.getTitleEn())) {
            LOGGER.debug("Invalid title en: " + lecture.getTitleEn());
            builder.setInvalidTitleEnAttr();
            invalidDataFlag = true;
        }
        if (!validation.descriptionValid((lecture.getDescription()))) {
            LOGGER.debug("Invalid description ukr: " + lecture.getDescription());
            builder.setInvalidDescriptionAttr();
            invalidDataFlag = true;
        }
        if (!validation.descriptionEnValid((lecture.getDescriptionEn()))) {
            LOGGER.debug("Invalid description en: " + lecture.getDescriptionEn());
            builder.setInvalidDescriptionEnAttr();
            invalidDataFlag = true;
        }
        if (lecture.getStartTime() != null) {
            if (!validation.startTimeValid(lecture.getStartTime(), lecture.getMainConference().getDateTime())) {
                LOGGER.debug("Invalid start time: " + lecture.getStartTime());
                builder.setInvalidLectureStartTimeAttr();
                invalidDataFlag = true;
            }
        }
        return invalidDataFlag ? builder.build() : null;
    }

    /**
     * The default method which get parameters from request and
     * generate them into {@link Lecture}
     *
     * @param request is an HttpServletRequest request
     * @return instance of {@link Lecture}
     */
    default Lecture getLectureFromRequest(HttpServletRequest request) {
        LOGGER.debug("Get lecture from request");
        Lecture lecture = new Lecture();
        lecture.setTitle(request.getParameter(AttributesManager.getProperty("title")));
        lecture.setTitleEn(request.getParameter(AttributesManager.getProperty("title.en")));
        lecture.setDescription(request.getParameter(AttributesManager.getProperty("description")));
        lecture.setDescriptionEn(request.getParameter(AttributesManager.getProperty("description.en")));
        return lecture;
    }
}
