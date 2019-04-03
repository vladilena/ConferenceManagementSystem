package controller.command.impl.moderator;

import controller.command.Command;
import model.dto.InvalidData;
import model.entity.Conference;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ChangeConferenceCommand implements Command, GenerateConferenceCommand {
    private static ConferenceService conferenceService;

    public ChangeConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Conference conference = getConferenceFromRequest(request);
        conference.setId(Long.valueOf(request.getParameter(AttributesManager.getProperty("conference.id"))));
        InvalidData invalidData = inputChecked(conference);

        if (invalidData == null) {
            LOGGER.info("Validation was succeed");
            tryToUpdateConference(conference, request);
        } else {
            LOGGER.debug("Invalid input");
            request.setAttribute(AttributesManager.getProperty("invalid.conference"), conference);
            request.setAttribute(AttributesManager.getProperty("invalid.input.conf"), invalidData);
        }
        return PathManager.getProperty("path.page.change.conference");
    }
    private void tryToUpdateConference(Conference conference, HttpServletRequest request){
        if (conferenceService.update(conference)) {
            LOGGER.info("Conference was updated");
            request.setAttribute(AttributesManager.getProperty("success.update"), true);
        } else {
            LOGGER.debug("Conference wasn't updated");
            request.setAttribute(AttributesManager.getProperty("not.update.conf"), true);
        }
    }
}


