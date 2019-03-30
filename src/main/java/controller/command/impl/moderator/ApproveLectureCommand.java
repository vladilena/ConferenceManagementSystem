package controller.command.impl.moderator;

import controller.command.Command;
import model.entity.Conference;
import model.service.ConferenceService;
import model.service.LectureService;
import model.service.impl.DefaultConferenceService;
import model.service.impl.DefaultLectureService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveLectureCommand implements Command {
    private static LectureService lectureService;
    private static ConferenceService conferenceService;

    public ApproveLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter("lecture_id"));
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        if (lectureService.approve(lectureId)) {
            Conference conference = conferenceService.getById(conferenceId);
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
        }else {
            request.setAttribute(AttributesManager.getProperty("not.approved"), true);
        }

        return PathManager.getProperty("path.page.conference");
    }
}


