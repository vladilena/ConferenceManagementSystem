package com.training.vladilena.controller.command.impl.redirect;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * The {@code RedirectConferenceCommand} class implements {@link Command}
 * and is used for redirect to the conference page and fill it with data.
 * Also to set {@link Conference} to the session
 *
 * @author Vladlena Ushakova
 */
public class RedirectConferenceCommand implements Command {
    private static ConferenceService conferenceService;

    public RedirectConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getParameter(AttributesManager.getProperty("conference.id")));
        request.getSession().setAttribute(AttributesManager.getProperty("conference.id"), conferenceId);

        Conference conference = conferenceService.getById(conferenceId);
        request.setAttribute(AttributesManager.getProperty("conference"), conference);
        if (conference.getDateTime().isAfter(LocalDateTime.ofInstant(Instant.ofEpochMilli(System.currentTimeMillis()), ZoneId.systemDefault()))) {
            request.getSession().setAttribute(AttributesManager.getProperty("future"), true);
        } else {
            request.getSession().removeAttribute(AttributesManager.getProperty("future"));
        }
        return PathManager.getProperty("path.page.conference");
    }
}


