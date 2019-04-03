package controller.command.impl.auth;

import controller.command.Command;
import model.dto.InvalidData;
import model.entity.Role;
import model.entity.User;
import model.service.UserService;
import model.service.impl.DefaultUserService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.UserValidation;
import model.validation.impl.DefaultUserValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Set;

public class RegistrationCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationCommand.class);
    private static UserService userService;
    private static UserValidation validation;

    public RegistrationCommand() {
        userService = DefaultUserService.getInstance();
        validation = DefaultUserValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String resultPage = PathManager.getProperty("path.page.registration");
        User user = getUserFromRequest(request);

        InvalidData invalidData = inputChecked(user);

        if (invalidData == null) {
            LOGGER.info("User is valid");
            resultPage = tryToCreateUser(request, user, resultPage);

        } else {
            LOGGER.debug("User is invalid");
            request.setAttribute(AttributesManager.getProperty("invalid.user"), user);
            request.setAttribute(AttributesManager.getProperty("invalid.registration.data"), invalidData);
        }

        return resultPage;
    }

    private User getUserFromRequest(HttpServletRequest request) {
        LOGGER.debug("Get user from request");
        User user = new User();
        user.setLogin(request.getParameter(AttributesManager.getProperty("login")));
        user.setEmail(request.getParameter(AttributesManager.getProperty("email")));
        user.setPassword(request.getParameter(AttributesManager.getProperty("password")));
        user.setFirstName(request.getParameter(AttributesManager.getProperty("first.name")));
        user.setFirstNameEn(request.getParameter(AttributesManager.getProperty("first.name.en")));
        user.setLastName(request.getParameter(AttributesManager.getProperty("last.name")));
        user.setLastNameEn(request.getParameter(AttributesManager.getProperty("last.name.en")));
        user.setRole(Role.valueOf(request.getParameter(AttributesManager.getProperty("role"))));
        return user;
    }

    private InvalidData inputChecked(User user) {
        InvalidData.Builder builder = InvalidData.newBuilder(AttributesManager.getProperty("invalidData"));
        boolean invalidDataFlag = false;

        if (!validation.loginValid(user.getLogin())) {
            LOGGER.debug("Invalid login: " + user.getLogin());
            builder.setInvalidLoginAttr();
            invalidDataFlag = true;
        }
        if (!validation.emailValid(user.getEmail())) {
            LOGGER.debug("Invalid email: " + user.getEmail());
            builder.setInvalidEmailAttr();
            invalidDataFlag = true;
        }
        if (!validation.firstNameValid(user.getFirstName())) {
            LOGGER.debug("Invalid first name in ukr: " + user.getFirstName());
            builder.setInvalidFirstNameAttr();
            invalidDataFlag = true;
        }
        if (!validation.firstNameEnValid(user.getFirstNameEn())) {
            LOGGER.debug("Invalid first name in en: " + user.getFirstNameEn());
            builder.setInvalidFirstNameEnAttr();
            invalidDataFlag = true;
        }
        if (!validation.lastNameValid(user.getLastName())) {
            LOGGER.debug("Invalid last name in ukr: " + user.getLastName());
            builder.setInvalidLastNameAttr();
            invalidDataFlag = true;
        }
        if (!validation.lastNameEnValid(user.getLastNameEn())) {
            LOGGER.debug("Invalid last name in en: " + user.getLastNameEn());
            builder.setInvalidLastNameEnAttr();
            invalidDataFlag = true;
        }
        if (!validation.passwordValid(user.getPassword())) {
            LOGGER.debug("Invalid pass: " + user.getPassword());
            builder.setInvalidPasswordAttr();
            invalidDataFlag = true;
        }

        return invalidDataFlag ? builder.build() : null;
    }

    private String tryToCreateUser(HttpServletRequest request, User user, String resultPage) {
        if (createUser(user)) {
            LOGGER.info("User was added to a database");
            user.setPassword("");
            request.setAttribute(AttributesManager.getProperty("success.registration"), true);
            request.getSession().setAttribute(AttributesManager.getProperty("user"), user);
            request.getServletContext().setAttribute(AttributesManager.getProperty("user"), user);
            resultPage = PathManager.getProperty("redirect.page.main");
        } else {
            LOGGER.debug("User wasn't added to a database");
            request.setAttribute(AttributesManager.getProperty("not.add.user"), true);
        }
        return resultPage;
    }

    private boolean createUser(User user) {
        boolean result = false;
        Set<String> logins = userService.getAllUsersLogins();
        if (!logins.contains(user.getLogin())) {
            try {
                result = userService.create(user);
            } catch (SQLException e) {
                LOGGER.error("Threw a SQLException: " + e);
            }
        }
        return result;
    }
}
