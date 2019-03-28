package model.service;

import model.entity.User;

import java.util.Set;

public interface UserService {
    Set<String> getAllUsersLogins();
    boolean create(User user);
    boolean subscribeOnConference(long userId, long conferenceId);
}
