package com.training.vladilena.model.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * The {@code Conference} class represents a conference which contains lists of
 * {@link Lecture} and {@link User}
 *
 * @author Vladlena Ushakova
 */
public class Conference {
    private long id;
    private String title;
    private String titleEn;
    private String description;
    private String descriptionEn;
    private LocalDateTime dateTime;
    private String place;
    private String placeEn;
    private int lecturesCapacity;
    private int placeCapacity;

    private List<User> registeredParticipants = new ArrayList<>();
    private List<Lecture> conferenceLectures = new ArrayList<>();


    public String getTitleEn() {
        return titleEn;
    }

    public void setTitleEn(String titleEn) {
        this.titleEn = titleEn;
    }

    public String getDescriptionEn() {
        return descriptionEn;
    }

    public void setDescriptionEn(String descriptionEn) {
        this.descriptionEn = descriptionEn;
    }

    public String getPlaceEn() {
        return placeEn;
    }

    public void setPlaceEn(String placeEn) {
        this.placeEn = placeEn;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public int getLecturesCapacity() {
        return lecturesCapacity;
    }

    public void setLecturesCapacity(int lecturesCapacity) {
        this.lecturesCapacity = lecturesCapacity;
    }

    public int getPlaceCapacity() {
        return placeCapacity;
    }

    public void setPlaceCapacity(int placeCapacity) {
        this.placeCapacity = placeCapacity;
    }

    public List<User> getRegisteredParticipants() {
        return registeredParticipants;
    }

    public void setRegisteredParticipants(List<User> registeredParticipants) {
        this.registeredParticipants = registeredParticipants;
    }

    public List<Lecture> getConferenceLectures() {
        return conferenceLectures;
    }

    public void setConferenceLectures(List<Lecture> conferenceLectures) {
        this.conferenceLectures = conferenceLectures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conference that = (Conference) o;
        return title.equals(that.title) &&
                description.equals(that.description) &&
                place.equals(that.place);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, description, place);
    }

    @Override
    public String toString() {
        return "\nConference{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", title_en='" + titleEn + '\'' +
                ", description='" + description + '\'' +
                ", description_en='" + descriptionEn + '\'' +
                ", dateTime=" + dateTime +
                ", place='" + place + '\'' +
                ", place_en='" + placeEn + '\'' +
                ", lecturesCapacity=" + lecturesCapacity +
                ", placeCapacity=" + placeCapacity +
                ", registeredParticipants=" + registeredParticipants +
                ", conferenceLectures=" + conferenceLectures +
                '}';
    }
}



