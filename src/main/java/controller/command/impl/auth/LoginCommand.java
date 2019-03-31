package controller.command.impl.auth;

import controller.command.Command;
import model.entity.User;
import model.service.LoginService;
import model.service.impl.DefaultLoginService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.UserValidation;
import model.validation.impl.DefaultUserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(LoginCommand.class);
    private static LoginService loginService;
    private static UserValidation validation;

    public LoginCommand() {
        loginService = DefaultLoginService.getInstance();
        validation = DefaultUserValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        User user = getUserFromDB(request.getParameter(AttributesManager.getProperty("login")),
                request.getParameter(AttributesManager.getProperty("password")));

        if (user != null) {
            LOGGER.debug("Registration was succeed with login: " + user.getLogin());
            request.getSession().setAttribute(AttributesManager.getProperty("user"), user);
            request.getServletContext().setAttribute(AttributesManager.getProperty("user"), user);
            return PathManager.getProperty("redirect.page.main");
        } else {
            LOGGER.debug("There is no such user in DB");
            request.setAttribute(AttributesManager.getProperty("loginError"), true);
            return PathManager.getProperty("path.page.login");
        }
    }

    private User getUserFromDB(String login, String password) {
        User user = null;
        if (checkLoginAndPassword(login, password)) {
            user = loginService.login(login, password);
        }else {
            LOGGER.debug("Invalid input for login or password");
        }
        return user;
    }

    private boolean checkLoginAndPassword(String login, String password) {
        return login != null && password != null
                && validation.loginValid(login)
                && validation.passwordValid(password);
    }
}

