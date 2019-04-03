package controller.command.impl.moderator;

import controller.command.Command;
import model.dto.InvalidData;
import model.entity.Conference;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.ConferenceValidation;
import model.validation.impl.DefaultConferenceValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;

public class CreateConferenceCommand implements Command, GenerateConferenceCommand {
    private static final Logger LOGGER = LogManager.getLogger(CreateConferenceCommand.class);
    private static ConferenceService conferenceService;

    public CreateConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

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
            request.setAttribute(AttributesManager.getProperty("invalid.input.conf"), invalidData);
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


