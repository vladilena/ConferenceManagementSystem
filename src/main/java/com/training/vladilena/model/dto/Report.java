package com.training.vladilena.model.dto;

import java.time.LocalDateTime;
/**
 * The {@code Report} class is a data transfer object that is used to create a Report after {@link com.training.vladilena.model.entity.Conference}
 *
 * @author Vladlena Ushakova
 */
public class Report {
    private int id;
    private String conferenceTitle;
    private String conferenceTitleEn;
    private LocalDateTime dateTime;
    private int providedLecturesAmount;
    private int registeredParticipantsAmount;
    private int actualParticipantsAmount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getConferenceTitle() {
        return conferenceTitle;
    }

    public void setConferenceTitle(String conferenceTitle) {
        this.conferenceTitle = conferenceTitle;
    }

    public String getConferenceTitleEn() {
        return conferenceTitleEn;
    }

    public void setConferenceTitleEn(String conferenceTitleEn) {
        this.conferenceTitleEn = conferenceTitleEn;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public int getProvidedLecturesAmount() {
        return providedLecturesAmount;
    }

    public void setProvidedLecturesAmount(int providedLecturesAmount) {
        this.providedLecturesAmount = providedLecturesAmount;
    }

    public int getRegisteredParticipantsAmount() {
        return registeredParticipantsAmount;
    }

    public void setRegisteredParticipantsAmount(int registeredParticipantsAmount) {
        this.registeredParticipantsAmount = registeredParticipantsAmount;
    }

    public int getActualParticipantsAmount() {
        return actualParticipantsAmount;
    }

    public void setActualParticipantsAmount(int actualParticipantsAmount) {
        this.actualParticipantsAmount = actualParticipantsAmount;
    }

    @Override
    public String toString() {
        return "Report: " + id+
                ", \nConference title in Ukrainian '" + conferenceTitle + '\'' +
                ", \nConference title in English '" + conferenceTitleEn + '\'' +
                ", \nDate and time " + dateTime +
                ", \nProvided lectures amount " + providedLecturesAmount +
                ", \nRegistered participants amount " + registeredParticipantsAmount +
                ", \nActual participants amount " + actualParticipantsAmount
                + "\n";
    }
}


