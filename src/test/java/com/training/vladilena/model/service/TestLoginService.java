package com.training.vladilena.model.service;

import com.training.vladilena.model.service.impl.DefaultLoginService;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertNull;


public class TestLoginService {
    private static LoginService loginService;

    @BeforeClass
    public static void init() {
        loginService = DefaultLoginService.getInstance();
    }

    @Test
    public void returnUserIfUserWithSuchLoginAndPassExists() {
        String login = "spik";
        String password = "spik";
        assertNotNull(loginService.login(login, password));
    }

    @Test
    public void returnNullIfUserWithSuchLoginAndPassNotExist() {
        String login = "spik_not_exist";
        String password = "spik_not_exist";
        assertNull(loginService.login(login, password));
    }
}


