package model.service;

import model.entity.User;

import java.sql.SQLException;
import java.util.Set;

public interface UserService {
    Set<String> getAllUsersLogins();
    boolean create(User user) throws SQLException;
    boolean subscribeOnConference(long userId, long conferenceId);
}
