package controller.command.impl.user;

import controller.command.Command;
import model.util.AttributesManager;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeLanguageCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        request.getSession().setAttribute(AttributesManager.getProperty("language"), request.getParameter(AttributesManager.getProperty("language")));
        LOGGER.debug("Set a locale into session and redirect to: " + PathManager.getProperty("redirect.page.main"));
        //TODO change path to current
        return PathManager.getProperty("redirect.page.main");
    }
}


