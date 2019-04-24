package com.training.vladilena.model.service;

import com.training.vladilena.model.service.impl.DefaultLoginService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;


class TestLoginService {
    private static LoginService loginService;

    @BeforeAll
    static void init() {
        loginService = DefaultLoginService.getInstance();
    }

    @Test
    void returnUserIfUserWithSuchLoginAndPassExists() {
        String login = "spik";
        String password = "spik";
        assertNotNull(loginService.login(login, password));
    }

    @Test
    void returnNullIfUserWithSuchLoginAndPassNotExist() {
        String login = "spik_not_exist";
        String password = "spik_not_exist";
        assertNull(loginService.login(login, password));
    }
}


