package controller.command.impl.auth;

import controller.command.Command;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LogoutCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.getServletContext().removeAttribute(AttributesManager.getProperty("user"));
        session.invalidate();
        return PathManager.getProperty("redirect.page.main");
    }
}


