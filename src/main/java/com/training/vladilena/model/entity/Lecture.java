package com.training.vladilena.model.entity;

import java.time.LocalDateTime;
import java.util.Objects;
/**
 * The {@code Lecture} class represents a lecture which related to {@link Conference}
 * and {@link Speaker}
 *
 * @author Vladlena Ushakova
 */
public class Lecture {
    private long id;
    private LocalDateTime startTime;
    private String title;
    private String titleEn;
    private String description;
    private String descriptionEn;
    private boolean isApproved;

    private Speaker mainSpeaker;
    private Conference mainConference;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
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

    public Speaker getMainSpeaker() {
        return mainSpeaker;
    }

    public void setMainSpeaker(Speaker mainSpeaker) {
        this.mainSpeaker = mainSpeaker;
    }

    public Conference getMainConference() {
        return mainConference;
    }

    public void setMainConference(Conference mainConference) {
        this.mainConference = mainConference;
    }

    public boolean isApproved() {
        return isApproved;
    }

    public void setApproved(boolean approved) {
        isApproved = approved;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lecture lecture = (Lecture) o;
        return isApproved == lecture.isApproved &&
                startTime.equals(lecture.startTime) &&
                title.equals(lecture.title) &&
                description.equals(lecture.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startTime, title, description, isApproved);
    }

    @Override
    public String toString() {
        return "\nLecture{" +
                "id=" + id +
                ", startTime=" + startTime +
                ", title='" + title + '\'' +
                ", title_en='" + titleEn + '\'' +
                ", description='" + description + '\'' +
                ", description_en='" + descriptionEn + '\'' +
                ", mainSpeaker=" + mainSpeaker +
                ", isApproved=" + isApproved +
                ", mainConference=" + mainConference +
                '}';
    }
}


