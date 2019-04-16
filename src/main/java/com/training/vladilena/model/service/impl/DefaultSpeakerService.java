package com.training.vladilena.model.service.impl;

import com.training.vladilena.model.dao.DaoFactory;
import com.training.vladilena.model.dao.SpeakerDao;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.service.SpeakerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.math.BigDecimal;
import java.util.List;
/**
 * {@inheritDoc}
 */
public class DefaultSpeakerService implements SpeakerService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultSpeakerService.class);

    private static volatile SpeakerService speakerService;
    private static SpeakerDao speakerDao;
    private static final BigDecimal MIN_COEFFICIENT = BigDecimal.valueOf(100);
    private static final BigDecimal AVR_COEFFICIENT = BigDecimal.valueOf(150);
    private static final BigDecimal MAX_COEFFICIENT = BigDecimal.valueOf(200);
    private static final double LOW_RATING_LEVEL = 10;
    private static final double HIGH_RATING_LEVEL = 15;

    private DefaultSpeakerService() {
        speakerDao = DaoFactory.getInstance().getSpeakerDao();
    }
    /**
     * Always return same {@link DefaultSpeakerService} instance
     *
     * @return always return same {@link DefaultSpeakerService} instance
     */
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
        return speakerDao.create(speaker);
    }

    @Override
    public boolean changeRating(double newRating, long speakerId) {
        return speakerDao.changeRating(newRating, speakerId);
    }

    @Override
    public boolean transferBonus(long speakerId) {
        Speaker speaker = speakerDao.findById(speakerId);
        BigDecimal bonus = calculateBonus(speaker.getRating());
        return speakerDao.transferBonus(bonus, speakerId);
    }

    @Override
    public boolean getBonus(long speakerId) {
        return speakerDao.transferBonus(BigDecimal.ZERO, speakerId);
    }


    private BigDecimal calculateBonus(double rating) {
        BigDecimal coefficient = BigDecimal.ONE;
        if (rating <= LOW_RATING_LEVEL) {
            coefficient = MIN_COEFFICIENT;
        } else if (rating > LOW_RATING_LEVEL && rating <= HIGH_RATING_LEVEL) {
            coefficient = AVR_COEFFICIENT;
        } else {
            coefficient = MAX_COEFFICIENT;
        }
        return BigDecimal.valueOf(rating).multiply(coefficient);
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


