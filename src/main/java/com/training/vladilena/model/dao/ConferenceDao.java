package com.training.vladilena.model.dao;

import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.User;

import java.util.List;

/**
 * The {@code ConferenceDao} implements {@link GenericDao}
 * interface for ORM database entity {@link Conference}
 *
 * @author Vladlena Ushakova
 */
public interface ConferenceDao extends GenericDao<Conference> {
    /**
     * Method to get all {@link Conference} with subscribed {@link User}s
     *
     * @return return {@link List} of all {@link Conference}
     */
    List<Conference> findAllSubscribed();
}
