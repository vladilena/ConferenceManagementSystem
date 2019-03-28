package model.validation.impl;

import model.validation.SpeakerValidation;

public class DefaultSpeakerValidation implements SpeakerValidation {
    private static volatile SpeakerValidation speakerValidation;

    private DefaultSpeakerValidation() {
    }

    public static SpeakerValidation getInstance(){
        SpeakerValidation localInstance = speakerValidation;
        if(localInstance == null) {
            synchronized (DefaultUserValidation.class) {
                localInstance = speakerValidation;
                if(localInstance == null) {
                    speakerValidation = new DefaultSpeakerValidation();
                    // logger.debug("Create first DefaultUserValidation instance");
                }
            }
        }
        // logger.debug("Return DefaultUserValidation instance");
        return speakerValidation;
    }



    @Override
    public boolean ratingValid(int rating) {
        return rating>0 && rating<20;
    }
}


