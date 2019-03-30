package controller.command.impl.auth;

import controller.command.Command;
import model.entity.User;
import model.service.LoginService;
import model.service.impl.DefaultLoginService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {
    private static LoginService loginService;

    public LoginCommand() {
        loginService = DefaultLoginService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        String login = request.getParameter("login");
        String password = request.getParameter("password");

        User user = getUser(login, password);

        if (user != null) {
            request.getSession().setAttribute(AttributesManager.getProperty("user.attribute"), user);
            request.getServletContext().setAttribute(AttributesManager.getProperty("user.attribute"), user);
            return PathManager.getProperty("redirect.page.main");
        }
        request.setAttribute(AttributesManager.getProperty("loginError"), true);
        return PathManager.getProperty("path.page.login");
    }

    private User getUser(String login, String password) {
        User user = null;

        if (checkLoginAndPassword(login, password)) {
            user = loginService.login(login, password);
        }

        return user;
    }

    private boolean checkLoginAndPassword(String login, String password) {
        return login != null && password != null;
    }
}


