package com.training.vladilena.controller.command;

import com.training.vladilena.model.dto.InvalidData;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.validation.ConferenceValidation;
import com.training.vladilena.model.validation.impl.DefaultConferenceValidation;
import com.training.vladilena.util.AttributesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
/**
 * The {@code GenerateConference} interface is used for get {@link Conference}'s parameters
 * from request and validate them
 *
 * @author Ushakova Vladlena
 */
public interface GenerateConference {
    Logger LOGGER = LogManager.getLogger(GenerateConference.class);
    ConferenceValidation validation = DefaultConferenceValidation.getInstance();
    /**
     * The default method which get parameters from request and
     * generate them into {@link Conference}
     * @param request is an HttpServletRequest request
     * @return instance of {@link Conference}
     */
    default Conference getConferenceFromRequest(HttpServletRequest request) {
        Conference conference = new Conference();
        conference.setTitle(request.getParameter(AttributesManager.getProperty("title.ukr")));
        conference.setTitleEn(request.getParameter(AttributesManager.getProperty("title.en")));
        conference.setDescription(request.getParameter(AttributesManager.getProperty("description.ukr")));
        conference.setDescriptionEn(request.getParameter(AttributesManager.getProperty("description.en")));
        conference.setDateTime(LocalDateTime.parse(request.getParameter(AttributesManager.getProperty("date.time"))));
        conference.setPlace(request.getParameter(AttributesManager.getProperty("place.ukr")));
        conference.setPlaceEn(request.getParameter(AttributesManager.getProperty("place.en")));
        conference.setLecturesCapacity(Integer.valueOf(request.getParameter(AttributesManager.getProperty("lectures.capacity"))));
        conference.setPlaceCapacity(Integer.valueOf(request.getParameter(AttributesManager.getProperty("place.capacity"))));
        return conference;
    }
    /**
     * The default method which get {@link Conference} and validate it
     * @param conference is a Conference to validate
     * @return {@link InvalidData} instance if conference is invalid, else return null
     */
    default  InvalidData inputChecked(Conference conference) {
        InvalidData.Builder builder = InvalidData.newBuilder(AttributesManager.getProperty("invalidData"));
        boolean invalidDataFlag = false;
        if (!validation.titleValid(conference.getTitle())) {
            LOGGER.debug("Invalid title in ukr: " + conference.getTitle());
            builder.setInvalidConfTitleAttr();
            invalidDataFlag = true;
        }
        if (!validation.titleEnValid(conference.getTitleEn())) {
            LOGGER.debug("Invalid title in en: " + conference.getTitleEn());
            builder.setInvalidConfTitleEnAttr();
            invalidDataFlag = true;
        }
        if (!validation.descriptionValid(conference.getDescription())) {
            LOGGER.debug("Invalid description in ukr: " + conference.getDescription());
            builder.setInvalidConfDescriptionAttr();
            invalidDataFlag = true;
        }
        if (!validation.descriptionEnValid(conference.getDescriptionEn())) {
            LOGGER.debug("Invalid description in en: " + conference.getDescriptionEn());
            builder.setInvalidConfDescriptionEnAttr();
            invalidDataFlag = true;
        }
        if (!validation.placeValid(conference.getPlace())) {
            LOGGER.debug("Invalid place in ukr: " + conference.getPlace());
            builder.setInvalidConfPlaceAttr();
            invalidDataFlag = true;
        }
        if (!validation.placeEnValid(conference.getPlaceEn())) {
            LOGGER.debug("Invalid place in en: " + conference.getPlaceEn());
            builder.setInvalidConfPlaceEnAttr();
            invalidDataFlag = true;
        }
        if (!validation.dateTimeValid(conference.getDateTime())) {
            LOGGER.debug("Invalid date or time: " + conference.getDateTime());
            builder.setInvalidConfDateTimeAttr();
            invalidDataFlag = true;
        }
        if (!validation.lecturesCapacityValid(conference.getLecturesCapacity())) {
            LOGGER.debug("Invalid lectures capacity: " + conference.getLecturesCapacity());
            builder.setInvalidConfLecturesCapacityAttr();
            invalidDataFlag = true;
        }
        if (!validation.placeCapacity(conference.getPlaceCapacity())) {
            LOGGER.debug("Invalid place capacity: " + conference.getPlaceCapacity());
            builder.setInvalidConfPlaceCapacityAttr();
            invalidDataFlag = true;
        }
        return invalidDataFlag ? builder.build() : null;
    }
}
