package com.training.vladilena.model.dao.data;

import com.training.vladilena.model.entity.Speaker;

public class SpeakerBuilder implements Builder<Speaker> {
    private Speaker speaker;

    private SpeakerBuilder() {
        speaker = new Speaker();
    }

    public static SpeakerBuilder getBuilder() {
        return new SpeakerBuilder();
    }

    public SpeakerBuilder constructSpeaker(Long template) {
        if (template != null) {
            speaker.setId(template);
            speaker.setLogin("login" + template);
            speaker.setPassword("password" + template);
            speaker.setEmail("email@ukr.net" + template);
            speaker.setFirstName("text");
            speaker.setFirstNameEn("text");
            speaker.setLastName("text");
            speaker.setLastNameEn("text");
            speaker.setRating(10);
            speaker.setBonus(10);
        }
        return this;
    }

    @Override
    public Speaker build() {
        return speaker;
    }
}


