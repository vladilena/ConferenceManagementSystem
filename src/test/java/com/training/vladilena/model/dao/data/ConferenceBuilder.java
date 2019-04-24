package com.training.vladilena.model.dao.data;

import com.training.vladilena.model.entity.Conference;

import java.time.LocalDateTime;

public class ConferenceBuilder implements Builder<Conference> {
    private Conference conference;


    private ConferenceBuilder() {
        conference = new Conference();
    }

    public static ConferenceBuilder getBuilder() {
        return new ConferenceBuilder();
    }

    @Override
    public Conference build() {
        return conference;
    }

    public ConferenceBuilder constructConference(Long template) {
        if (template != null) {
            conference.setId(template);
            conference.setTitle("text" + template);
            conference.setTitleEn("text" + template);
            conference.setDescription("text" + template);
            conference.setDescriptionEn("text" + template);
            conference.setDateTime(LocalDateTime.of(2019, 2, 12, 15, 0));
            conference.setPlace("text" + template);
            conference.setPlaceEn("text" + template);
            conference.setLecturesCapacity(10);
            conference.setPlaceCapacity(10);
        }
        return this;
    }
}
