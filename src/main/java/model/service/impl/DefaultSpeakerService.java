package model.service.impl;

import model.dao.DaoFactory;
import model.dao.SpeakerDao;
import model.entity.Speaker;
import model.service.SpeakerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class DefaultSpeakerService implements SpeakerService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultSpeakerService.class);

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
                      LOGGER.debug("Create first DefaultSpeakerService instance");
                }
            }
        }
        LOGGER.debug("Return DefaultSpeakerService instance");
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

    @Override
    public Speaker getById(long id) {
        return speakerDao.findById(id);
    }
}


