package com.training.vladilena.controller.command.impl.auth;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.GenerateUser;
import com.training.vladilena.model.dto.InvalidData;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.service.UserService;
import com.training.vladilena.model.service.impl.DefaultUserService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import com.training.vladilena.util.SecurityHash;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Set;
/**
 * The {@code RegistrationCommand} class implements {@link Command},{@link GenerateUser}
 * and is used for new user creation and log in after
 *
 * @author Vladlena Ushakova
 */
public class RegistrationCommand implements Command, GenerateUser {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private static UserService userService;

    public RegistrationCommand() {
        userService = DefaultUserService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultPage = PathManager.getProperty("path.page.registration");
        User user = getUserFromRequest(request);
        InvalidData invalidData = inputChecked(user);

        if (invalidData == null) {
            LOGGER.info("User is valid");
            user.setPassword(new SecurityHash().hashFunction(user.getPassword()));
            resultPage = tryToCreateUser(request, user);

        } else {
            LOGGER.debug("User is invalid");
            request.setAttribute(AttributesManager.getProperty("invalid.user"), user);
            request.setAttribute(AttributesManager.getProperty("invalid.data"), invalidData);
        }
        return resultPage;
    }

    private String tryToCreateUser(HttpServletRequest request, User user) {
        String resultPage = PathManager.getProperty("path.page.registration");
        if (createUser(user, request)) {
            LOGGER.info("User was added to a database");
            user.setPassword("");
            loginUser(request, user);
            resultPage = PathManager.getProperty("redirect.page.main");
        } else {
            LOGGER.debug("User wasn't added to a database");
            request.setAttribute(AttributesManager.getProperty("not.add.user"), true);
        }
        return resultPage;
    }

    private boolean createUser(User user, HttpServletRequest request) {
        boolean result = false;
        Set<String> logins = userService.getAllUsersLogins();
        if (!logins.contains(user.getLogin())) {
            try {
                result = userService.create(user);
            } catch (SQLException e) {
                LOGGER.error("Threw a SQLException: " + e);
            }
        } else {
            LOGGER.debug("User with this login or email already exists");
            request.setAttribute(AttributesManager.getProperty("user.already.exists"), true);
        }
        return result;
    }

    private static void loginUser(HttpServletRequest request, User user) {
        if (request.getSession().getServletContext().getAttribute(user.getLogin()) == null) {
            user.setPassword("");
            request.getSession().setAttribute(AttributesManager.getProperty("user"), user);
            request.getSession().getServletContext().setAttribute(user.getLogin(), true);
        }
    }
}
