package model.service;

import model.entity.User;

public interface LoginService {
    User login(String login, String password);
}
