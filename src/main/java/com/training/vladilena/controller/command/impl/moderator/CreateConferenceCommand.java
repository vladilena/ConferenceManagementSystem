package com.training.vladilena.controller.command.impl.moderator;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.GenerateConference;
import com.training.vladilena.model.dto.InvalidData;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code CreateConferenceCommand} class implements {@link Command}
 * and is used for creating the new {@link Conference} by Moderator
 *
 * @author Vladlena Ushakova
 */
public class CreateConferenceCommand implements Command, GenerateConference {
    private static final Logger LOGGER = LogManager.getLogger(CreateConferenceCommand.class);
    private static ConferenceService conferenceService;

    public CreateConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Conference conference = getConferenceFromRequest(request);
        InvalidData invalidData = inputChecked(conference);

        if (invalidData == null) {
            LOGGER.info("Validation was succeed");
            tryToCreateConference(conference, request);
        } else {
            LOGGER.debug("Invalid input");
            request.setAttribute(AttributesManager.getProperty("invalid.conference"), conference);
            request.setAttribute(AttributesManager.getProperty("invalid.data"), invalidData);
        }
        return PathManager.getProperty("path.page.create.conference");
    }

    private void tryToCreateConference(Conference conference, HttpServletRequest request) {
        if (conferenceService.create(conference)) {
            LOGGER.info("Conference was created");
            request.setAttribute(AttributesManager.getProperty("success.offer"), true);
        } else {
            LOGGER.debug("Conference wasn't created");
            request.setAttribute(AttributesManager.getProperty("not.add.conf"), true);
        }
    }
}


