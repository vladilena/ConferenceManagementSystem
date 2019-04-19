package com.training.vladilena.model.dao;

import com.training.vladilena.model.entity.Conference;

import java.util.List;

/**
 * The {@code ConferenceDao} implements {@link GenericDao}
 * interface for ORM database entity {@link Conference}
 *
 * @author Vladlena Ushakova
 */
public interface ConferenceDao extends GenericDao<Conference> {
    List<Conference> findAllSubscribed();
}
