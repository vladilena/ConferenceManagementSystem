package controller.command.impl.conference;

import controller.command.Command;
import model.service.ConferenceService;
import model.service.impl.DefaultConferenceService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FindConferencesByTimeCommand implements Command {
    private static ConferenceService conferenceService;

    public FindConferencesByTimeCommand() {
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {








        return null;
    }
}


