package com.training.vladilena.controller.command;

import com.training.vladilena.model.dto.InvalidData;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.validation.UserValidation;
import com.training.vladilena.model.validation.impl.DefaultUserValidation;
import com.training.vladilena.util.AttributesManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * The {@code GenerateUser} interface is used for get {@link User}'s parameters
 * from request or session and validate them
 *
 * @author Ushakova Vladlena
 */
public interface GenerateUser {
    Logger LOGGER = LogManager.getLogger(GenerateUser.class);
    UserValidation validation = DefaultUserValidation.getInstance();

    /**
     * The default method which get parameters from session and
     * generate them into {@link User}
     *
     * @param request is an HttpServletRequest request
     * @return instance of {@link User}
     */
    default User getUserFromSession(HttpServletRequest request) {
        User user = null;
        if (request.getSession() != null) {
            user = (User) request.getSession().getAttribute(AttributesManager.getProperty("user"));
        }
        return user;
    }

    /**
     * The default method which get parameters from request and
     * generate them into {@link User}
     *
     * @param request is an HttpServletRequest request
     * @return instance of {@link User}
     */
    default User getUserFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter(AttributesManager.getProperty("login")).trim());
        user.setEmail(request.getParameter(AttributesManager.getProperty("email")).trim());
        user.setPassword(request.getParameter(AttributesManager.getProperty("password")).trim());
        user.setFirstName(request.getParameter(AttributesManager.getProperty("first.name")).trim());
        user.setFirstNameEn(request.getParameter(AttributesManager.getProperty("first.name.en")).trim());
        user.setLastName(request.getParameter(AttributesManager.getProperty("last.name")).trim());
        user.setLastNameEn(request.getParameter(AttributesManager.getProperty("last.name.en")).trim());
        user.setRole(Role.valueOf(request.getParameter(AttributesManager.getProperty("role"))));
        return user;
    }

    /**
     * The default method which get {@link User} and validate it
     *
     * @param user is a User to validate
     * @return {@link InvalidData} instance if conference is invalid, else return null
     */
    default InvalidData inputChecked(User user) {
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
}
