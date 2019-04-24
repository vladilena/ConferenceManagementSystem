package com.training.vladilena.controller.command.impl.redirect;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code RedirectChangeConferenceCommand} class implements {@link Command}
 * and is used for redirect to the conference page and fill it with data
 *
 * @author Vladlena Ushakova
 */
public class RedirectChangeConferenceCommand implements Command {
    private static ConferenceService conferenceService;

    public RedirectChangeConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        Conference conference = conferenceService.getById(conferenceId);
        request.setAttribute(AttributesManager.getProperty("conference"), conference);
        return PathManager.getProperty("path.page.change.conference");
    }
}


