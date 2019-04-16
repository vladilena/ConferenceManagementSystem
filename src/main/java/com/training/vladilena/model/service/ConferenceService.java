package com.training.vladilena.model.service;

import com.training.vladilena.model.entity.Conference;

import java.util.List;
import com.training.vladilena.model.dao.ConferenceDao;
/**
 * The {@code ConferenceService} service is a specified API for working with the {@link ConferenceDao}
 *
 * @author Vladlena Ushakova
 */
public interface ConferenceService {
    /**
     * Method to get all {@link Conference}s which are ongoing
     *
     * @param currentTIme is a current time
     * @return return {@link List} of all ongoing {@link Conference} depends on {@code millis}
     */
    List<Conference> getOngoingConferences(long currentTIme);
    /**
     * Method to get all {@link Conference}s which are past
     *
     * @param currentTIme is a current time
     * @return return {@link List} of all past {@link Conference} depends on {@code millis}
     */
    List<Conference> getLastConferences(long currentTIme);
    /**
     * Method to return {@link Conference} which find by {@code id}
     *
     * @param id indicates an {@link Conference} {@code id} that you want to return
     * @return return {@link Conference} by {@code id}
     */
    Conference getById(long id);
    /**
     * Method to create {@link Conference}
     *
     * @param conference this {@code conference} will be created
     * @return returns {@code true} if {@link Conference} was created succeed
     *         or else {@code false}
     */
    boolean create(Conference conference);
    /**
     * Method to update {@link Conference}
     *
     * @param conference the {@code conference} which will be updated
     * @return returns {@code true} if the {@code conference} was updated
     *         or else {@code false}
     */
    boolean update(Conference conference);
    /**
     * Method delete {@link Conference}
     *
     * @param conferenceId the {@code conference} will be deleted by this {@code id}
     * @return returns {@code true} if the {@code conference} was deleted
     *         or else {@code false}
     */
    boolean delete(long conferenceId);
    /**
     * Method to get all {@link Conference}s which ongoing on {@code millis} time
     *
     * @param millis is a time to conferences
     * @return return {@link List} of all {@link Conference} depends on {@code millis}
     */
    List<Conference> getOngoingInOneWeekOrLess(long millis);
}
