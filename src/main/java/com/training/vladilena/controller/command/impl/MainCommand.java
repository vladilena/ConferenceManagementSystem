package com.training.vladilena.controller.command.impl;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.GenerateUser;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * The {@code MainCommand} class implements {@link Command}
 * and is used for filling the data on main page
 *
 * @author Vladlena Ushakova
 */
public class MainCommand implements Command, GenerateUser {
    private static ConferenceService conferenceService;

    public MainCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = getUserFromSession(request);
        if (user != null) {
            long currentTIme = System.currentTimeMillis();
            List<Conference> ongoing = conferenceService.getOngoingConferences(currentTIme);
            List<Conference> last = conferenceService.getLastConferences(currentTIme);

            request.setAttribute(AttributesManager.getProperty("ongoing.attribute"), ongoing);
            request.setAttribute(AttributesManager.getProperty("last.attribute"), last);
        }
        return PathManager.getProperty("path.page.main");
    }
}


