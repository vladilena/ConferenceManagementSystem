package controller.command.impl;

import controller.command.Command;
import model.dto.InvalidData;
import model.entity.Conference;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.ConferenceValidation;
import model.validation.impl.DefaultConferenceValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class CreateConferenceCommand implements Command {
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

    if(invalidData==null){
        //data valid
        if(conferenceService.create(conference)){
            //created
            request.setAttribute(AttributesManager.getProperty("success.offer"), true);
        }else {
            //not add
            request.setAttribute(AttributesManager.getProperty("not.add.conf"), true);
        }

    }else {
        request.setAttribute(AttributesManager.getProperty("invalid.input.conf"), true);
    }
        return PathManager.getProperty("path.page.create.conference");
    }

    private Conference getConferenceFromRequest(HttpServletRequest request) {
        Conference conference = new Conference();
        conference.setTitle(request.getParameter("title_ukr"));
        conference.setTitleEn(request.getParameter("title_en"));
        conference.setDescription(request.getParameter("description_ukr"));
        conference.setDescriptionEn(request.getParameter("description_en"));
        conference.setDateTime(LocalDateTime.parse(request.getParameter("date_time")));
        conference.setPlace(request.getParameter("place_ukr"));
        conference.setPlaceEn(request.getParameter("place_en"));
        conference.setLecturesCapacity(Integer.valueOf(request.getParameter("lectures_capacity")));
        conference.setPlaceCapacity(Integer.valueOf(request.getParameter("place_capacity")));
        return conference;
    }
    private InvalidData inputChecked (Conference conference){
        InvalidData.Builder builder = InvalidData.newBuilder("invalidData");
        boolean invalidDataFlag = false;
        if(!validation.titleValid(conference.getTitle())){
            builder.setInvalidConfTitleAttr();
            invalidDataFlag = true;
        }
        if(!validation.titleEnValid(conference.getTitleEn())){
            builder.setInvalidConfTitleEnAttr();
            invalidDataFlag = true;
        }
        if(!validation.descriptionValid(conference.getDescription())){
            builder.setInvalidConfDescriptionAttr();
            invalidDataFlag = true;
        }
        if(!validation.descriptionEnValid(conference.getDescriptionEn())){
            builder.setInvalidConfDescriptionEnAttr();
            invalidDataFlag = true;
        }
        if(!validation.placeValid(conference.getPlace())){
            builder.setInvalidConfPlaceAttr();
            invalidDataFlag = true;
        }
        if(!validation.placeEnValid(conference.getPlaceEn())){
            builder.setInvalidConfPlaceEnAttr();
            invalidDataFlag = true;
        }
        if(!validation.dateTimeValid(conference.getDateTime())){
            builder.setInvalidConfDateTimeAttr();
            invalidDataFlag = true;
        }
        if(!validation.lecturesCapacityValid(conference.getLecturesCapacity())){
            builder.setInvalidConfLecturesCapacityAttr();
            invalidDataFlag = true;
        }
        if(!validation.placeCapacity(conference.getPlaceCapacity())){
            builder.setInvalidConfPlaceCapacityAttr();
            invalidDataFlag = true;
        }
        return invalidDataFlag ? builder.build() : null;
    }
}


