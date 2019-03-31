package controller.command.impl.moderator;

import controller.command.Command;
import model.dto.InvalidData;
import model.entity.Conference;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.ConferenceValidation;
import model.validation.impl.DefaultConferenceValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class CreateConferenceCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateConferenceCommand.class);
    private static ConferenceService conferenceService;
    private static ConferenceValidation validation;

    public CreateConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
        validation = DefaultConferenceValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Conference conference = getConferenceFromRequest(request);
        InvalidData invalidData = inputChecked(conference);

        if (invalidData == null) {
            LOGGER.info("Validation was succeed");
            tryToCreateConference(conference, request);
        } else {
            LOGGER.debug("Invalid input");
            request.setAttribute(AttributesManager.getProperty("invalid.input.conf"), true);
        }
        return PathManager.getProperty("path.page.create.conference");
    }

    private Conference getConferenceFromRequest(HttpServletRequest request) {
        LOGGER.info("Get conference from request");
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

    private InvalidData inputChecked(Conference conference) {
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

    private void tryToCreateConference(Conference conference, HttpServletRequest request) {
        if (conferenceService.create(conference)) {
            LOGGER.info("Conference was created");
            request.setAttribute(AttributesManager.getProperty("success.offer"), true);
        } else {
            LOGGER.debug("Conference wasn't created");
            request.setAttribute(AttributesManager.getProperty("not.add.conf"), true);
        }
    }
}


