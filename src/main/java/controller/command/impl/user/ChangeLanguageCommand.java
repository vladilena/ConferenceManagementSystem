package controller.command.impl.user;

import controller.command.Command;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute("language",request.getParameter(AttributesManager.getProperty("language")));
        return PathManager.getProperty("redirect.page.main");
    }
}


