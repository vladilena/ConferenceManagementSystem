package com.training.vladilena.model.service;

import com.training.vladilena.model.entity.User;

import java.sql.SQLException;
import java.util.Set;
import com.training.vladilena.model.dao.UserDao;
import com.training.vladilena.model.entity.Conference;
/**
 * The {@code UserService} service is a specified API for working with the {@link UserDao}
 *
 * @author Vladlena Ushakova
 */
public interface UserService {
    /**
     * Method to get all {@link User}'s logins
     *
     * @return returns list of strings if search was succeed
     *         or else {@code null}
     */
    Set<String> getAllUsersLogins();
    /**
     * Method to create {@link User}
     *
     * @param user this {@code user} will be created
     * @return returns {@code true} if {@link User} was created succeed
     *         or else {@code false}
     */
    boolean create(User user) throws SQLException;
    /**
     * Method to subscribe {@link User} on {@link Conference}
     *
     * @param userId this {@code user} will be subscribed
     * @param conferenceId on this {@code conference} will be subscribed
     * @return returns {@code true} if {@link User} was subscribed succeed
     *         or else {@code false}
     */
    boolean subscribeOnConference(long userId, long conferenceId);
}
