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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class RegistrationCommand implements Command {
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

        if (invalidData==null) {
            // logger.info("User are valid");
            if (createUser(user)) {
                // logger.info("User was added to a database");
                request.setAttribute(AttributesManager.getProperty("success.registration"), true);
                request.getSession().setAttribute(AttributesManager.getProperty("user.attribute"), user);
               request.getServletContext().setAttribute(AttributesManager.getProperty("user.attribute"), user);
                resultPage = PathManager.getProperty("redirect.page.main");
            } else {
                // logger.debug("User wasn't added to a database");
                request.setAttribute(AttributesManager.getProperty("not.add.user"), true);
            }
        } else {
            //logger.debug("User are invalid");
            request.setAttribute(AttributesManager.getProperty("invalid.registration.data"), invalidData);
        }
        return resultPage;
    }

    private User getUserFromRequest(HttpServletRequest request) {
        User user = new User();
        user.setLogin(request.getParameter("login"));
        user.setEmail(request.getParameter("email"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setFirstNameEn(request.getParameter("firstName_en"));
        user.setLastName(request.getParameter("lastName"));
        user.setLastNameEn(request.getParameter("lastName_en"));
        user.setRole(Role.valueOf(request.getParameter("role")));
        return user;
    }

    private InvalidData inputChecked(User user) {
        InvalidData.Builder builder = InvalidData.newBuilder("invalidData");
        boolean invalidDataFlag = false;

        if(!validation.loginValid(user.getLogin())){
            builder.setInvalidLoginAttr();
            invalidDataFlag = true;
        }
        if(!validation.emailValid(user.getEmail())) {
            builder.setInvalidEmailAttr();
            invalidDataFlag = true;
        }
        if(!validation.firstNameValid(user.getFirstName())) {
            builder.setInvalidFirstNameAttr();
            invalidDataFlag = true;
        }
        if(!validation.firstNameEnValid(user.getFirstNameEn())) {
            builder.setInvalidFirstNameEnAttr();
            invalidDataFlag = true;
        }
        if(!validation.lastNameValid(user.getLastName())) {
            builder.setInvalidLastNameAttr();
            invalidDataFlag = true;
        }
        if(!validation.lastNameEnValid(user.getLastNameEn())) {
            builder.setInvalidLastNameEnAttr();
            invalidDataFlag = true;
        }
        if(!validation.passwordValid(user.getPassword())) {
            builder.setInvalidPasswordAttr();
            invalidDataFlag = true;
        }

        return invalidDataFlag ? builder.build() : null;
    }


    private boolean createUser(User user) {
        boolean result = false;
        Set<String> logins = userService.getAllUsersLogins();
        if (!logins.contains(user.getLogin())) {
            result = userService.create(user);
        }
    return result;}
}



