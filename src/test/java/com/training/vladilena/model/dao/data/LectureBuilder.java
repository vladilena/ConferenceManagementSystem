package com.training.vladilena.model.dao.data;

import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.Speaker;

import java.time.LocalDateTime;

public class LectureBuilder implements Builder<Lecture> {
    private Lecture lecture;

    private LectureBuilder() {
        lecture = new Lecture();
    }

    public static LectureBuilder getBuilder() {
        return new LectureBuilder();
    }

    public LectureBuilder constructLecture(Long template, Boolean approved, Builder<Conference> conferenceBuilder, Builder<Speaker> speakerBuilder) {
        if (template != null) {
            lecture.setId(template);
            lecture.setStartTime(LocalDateTime.of(2019, 2, 12, 15, 0));
            lecture.setTitle("text");
            lecture.setTitleEn("text");
            lecture.setDescription("text");
            lecture.setDescriptionEn("text");
            lecture.setApproved(approved);

            lecture.setMainSpeaker(speakerBuilder != null ? speakerBuilder.build() : null);
            lecture.setMainConference(conferenceBuilder != null ? conferenceBuilder.build() : null);
        }
        return this;
    }

    @Override
    public Lecture build() {
        return lecture;
    }
}
