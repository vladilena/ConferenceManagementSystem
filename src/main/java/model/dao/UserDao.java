package model.dao;

import model.entity.User;

import java.util.Set;

public interface UserDao extends GenericDao<User> {
    User findByLogin(String login);

    Set<String> getAllLogins();

    long findIdByLogin(String login);

    boolean subscribeOnConference(long userId, long conferenceId);
}
