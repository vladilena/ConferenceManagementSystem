package controller.command.impl.moderator;

import model.dto.InvalidData;
import model.entity.Conference;
import model.util.AttributesManager;
import model.validation.ConferenceValidation;
import model.validation.impl.DefaultConferenceValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

public interface GenerateConferenceCommand {
    Logger LOGGER = LogManager.getLogger(GenerateConferenceCommand.class);
    ConferenceValidation validation = DefaultConferenceValidation.getInstance();

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
