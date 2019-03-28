package model.service.impl;

import model.dao.DaoFactory;
import model.dao.SpeakerDao;
import model.entity.Speaker;
import model.service.SpeakerService;

import java.util.List;

public class DefaultSpeakerService implements SpeakerService {

    private static volatile SpeakerService speakerService;
    private static SpeakerDao speakerDao;



    private DefaultSpeakerService() {
        speakerDao = DaoFactory.getInstance().getSpeakerDao();
    }

    public static SpeakerService getInstance() {
        SpeakerService localInstance = speakerService;
        if (localInstance == null) {
            synchronized (DefaultLoginService.class) {
                localInstance = speakerService;
                if (localInstance == null) {
                    speakerService = new DefaultSpeakerService();
                    //  logger.debug("Create first DefaultLoginService instance");
                }
            }
        }
        //logger.debug("Return DefaultLoginService instance");
        return speakerService;
    }


    @Override
    public boolean create(long userId) {
        Speaker speaker = new Speaker();
        speaker.setId(userId);
        speaker.setRating(0.0);
        speaker.setBonus(0.0);
        return speakerDao.create(speaker);
    }

    @Override
    public boolean changeRating(int newRating, long speakerId) {
        return speakerDao.changeRating(newRating, speakerId);
    }

    @Override
    public List<Speaker> getAll() {
        return speakerDao.findAll();
    }
}


