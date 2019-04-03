package model.service.impl;

import model.dao.ConferenceDao;
import model.dao.DaoFactory;
import model.entity.Conference;
import model.service.ConferenceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DefaultConferenceService implements ConferenceService {
    private static final Logger LOGGER = LogManager.getLogger(DefaultConferenceService.class);

    private static volatile ConferenceService conferenceService;
    private static ConferenceDao conferenceDAO;


    private DefaultConferenceService() {
        conferenceDAO = DaoFactory.getInstance().getConferenceDao();
    }

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


    @Override
    public List<Conference> getOngoingConferences(long currentTIme) {
        List<Conference> ongoing = conferenceDAO.findAll();
        return ongoing.stream()
                .filter(s -> s.getDateTime().isAfter(fromMillisToLocalDateTime(currentTIme)))
                .sorted(Comparator.comparing(Conference::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public List<Conference> getLastConferences(long currentTIme) {
        List<Conference> past = conferenceDAO.findAll();
        return past.stream()
                .filter(s -> s.getDateTime().isBefore(fromMillisToLocalDateTime(currentTIme)))
                .sorted(Comparator.comparing(Conference::getDateTime).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Conference getById(long id) {
        return conferenceDAO.findById(id);
    }

    @Override
    public boolean create(Conference conference) {
        return conferenceDAO.create(conference);
    }

    @Override
    public boolean update(Conference conference) {
        return conferenceDAO.update(conference);
    }

    @Override
    public boolean delete(long conferenceId) {
        return conferenceDAO.delete(conferenceId);
    }


    private LocalDateTime fromMillisToLocalDateTime(long millis) {
        return LocalDateTime.ofInstant(Instant.ofEpochMilli(millis), ZoneId.systemDefault());
    }
}


