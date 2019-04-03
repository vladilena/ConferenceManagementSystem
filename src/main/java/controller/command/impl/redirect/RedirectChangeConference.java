package controller.command.impl.redirect;

import controller.command.Command;
import model.entity.Conference;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectChangeConference implements Command {
    private static ConferenceService conferenceService;

    public RedirectChangeConference() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        Conference conference = conferenceService.getById(conferenceId);
        request.setAttribute(AttributesManager.getProperty("conference"), conference);
        return PathManager.getProperty("path.page.change.conference");
    }
}


