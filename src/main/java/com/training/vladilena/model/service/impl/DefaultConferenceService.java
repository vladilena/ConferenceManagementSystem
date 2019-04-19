package com.training.vladilena.model.service.impl;

import com.training.vladilena.model.dao.ConferenceDao;
import com.training.vladilena.model.dao.DaoFactory;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.service.ConferenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
/**
 * {@inheritDoc}
 */
public class DefaultConferenceService implements ConferenceService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultConferenceService.class);

    private static volatile ConferenceService conferenceService;
    private static ConferenceDao conferenceDAO;
    private static final int ONGOING_DAYS = 7;

    private DefaultConferenceService() {
        conferenceDAO = DaoFactory.getInstance().getConferenceDao();
    }
    /**
     * Always return same {@link DefaultConferenceService} instance
     *
     * @return always return same {@link DefaultConferenceService} instance
     */
    public static ConferenceService getInstance() {
        ConferenceService localInstance = conferenceService;
        if (localInstance == null) {
            synchronized (DefaultConferenceService.class) {
                localInstance = conferenceService;
                if (localInstance == null) {
                    conferenceService = new DefaultConferenceService();
                    LOGGER.debug("Create DefaultConferenceService instance");
                }
            }
        }
        LOGGER.debug("Return DefaultConferenceService instance");
        return conferenceService;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Conference> getOngoingConferences(long currentTIme) {
        List<Conference> ongoing = conferenceDAO.findAll();
        return ongoing.stream()
                .filter(s -> s.getDateTime().isAfter(fromMillisToLocalDateTime(currentTIme)))
                .sorted(Comparator.comparing(Conference::getDateTime).reversed())
                .collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Conference> getLastConferences(long currentTIme) {
        List<Conference> past = conferenceDAO.findAll();
        return past.stream()
                .filter(s -> s.getDateTime().isBefore(fromMillisToLocalDateTime(currentTIme)))
                .sorted(Comparator.comparing(Conference::getDateTime).reversed())
                .collect(Collectors.toList());
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Conference getById(long id) {
        return conferenceDAO.findById(id);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean create(Conference conference) {
        return conferenceDAO.create(conference);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean update(Conference conference) {
        return conferenceDAO.update(conference);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean delete(long conferenceId) {
        return conferenceDAO.delete(conferenceId);
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public List<Conference> getOngoingInOneWeekOrLess(long millis) {
        List<Conference> conferences = conferenceDAO.findAllSubscribed();
        LocalDateTime currentTime = fromMillisToLocalDateTime(millis);
        return conferences.stream()
                .filter(s -> s.getDateTime().plusDays(ONGOING_DAYS).isAfter(currentTime))
                .collect(Collectors.toList());
    }


    private LocalDateTime fromMillisToLocalDateTime(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }
}


