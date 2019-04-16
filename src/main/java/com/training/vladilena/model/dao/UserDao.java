package com.training.vladilena.model.dao;

import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.entity.Conference;

import java.util.List;
import java.util.Set;
/**
 * The {@code UserDao} implements {@link GenericDao}
 * interface for ORM database entity {@link User}
 *
 * @author Vladlena Ushakova
 */
public interface UserDao extends GenericDao<User> {
    /**
     * Method return {@link User} which find by {@code login}
     *
     * @param login it indicates an {@link User} {@code login} that you want to return
     * @return return {@link User} by {@code login}
     */
    User findByLogin(String login);
    /**
     * Method to get all {@link User}'s {@code logins}
     * @return return {@link List} of all {@link User}'s logins
     */
    Set<String> getAllLogins();
    /**
     * Method return {@link User}'s {@code id} which find by {@code login}
     *
     * @param login it indicates an {@link User} {@code login} that you want to gind
     * @return return {@link User}'s {@code id} by {@code login}
     */
    long findIdByLogin(String login);
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
