package controller.command.impl;

import controller.command.Command;
import model.entity.Conference;
import model.entity.User;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class MainCommand implements Command {
    private static ConferenceService conferenceService;


    public MainCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesManager.getProperty("user"));
        if (user != null) {
            long currentTIme = request.getSession().getCreationTime();
            List<Conference> ongoing = conferenceService.getOngoingConferences(currentTIme);
            List<Conference> last = conferenceService.getLastConferences(currentTIme);

            request.setAttribute(AttributesManager.getProperty("ongoing.attribute"), ongoing);
            request.setAttribute(AttributesManager.getProperty("last.attribute"), last);
        }
        return PathManager.getProperty("path.page.main");
    }
}


