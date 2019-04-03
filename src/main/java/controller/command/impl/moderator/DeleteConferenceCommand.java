package controller.command.impl.moderator;

import controller.command.Command;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;
import model.util.AttributesManager;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteConferenceCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteConferenceCommand.class);
    private static ConferenceService conferenceService;

    public DeleteConferenceCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long conferenceId = Long.valueOf(request.getParameter(AttributesManager.getProperty("conference.id")));
        if (conferenceService.delete(conferenceId)) {
            LOGGER.debug("Delete was succeed");
            return PathManager.getProperty("redirect.page.main");
        }
        LOGGER.debug("Delete wasn't succeed");
        request.setAttribute(AttributesManager.getProperty("conference.id"), conferenceId);
        return PathManager.getProperty("path.page.change.conference");
    }
}


