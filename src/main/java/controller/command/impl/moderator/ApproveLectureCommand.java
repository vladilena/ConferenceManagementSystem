package controller.command.impl.moderator;

import controller.command.Command;
import model.entity.Conference;
import model.service.ConferenceService;
import model.service.LectureService;
import model.service.impl.DefaultConferenceService;
import model.service.impl.DefaultLectureService;
import model.util.AttributesManager;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ApproveLectureCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ApproveLectureCommand.class);
    private static LectureService lectureService;
    private static ConferenceService conferenceService;

    public ApproveLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
        Conference conference = getConferenceFromRequest(request);

        if (lecturesCapacityEnough(conference)) {
            LOGGER.debug("Lectures capacity is enough for this conference");
            tryToApproveLecture(request, lectureId, conference);
        } else {
            request.setAttribute(AttributesManager.getProperty("not.enough.lectures.capacity"), true);
            LOGGER.debug("Lecture wasn't approved. Not enough capacity");
        }

        return PathManager.getProperty("path.page.conference");
    }

    private boolean lecturesCapacityEnough(Conference conference) {
        return conference.getConferenceLectures().size() < conference.getLecturesCapacity();
    }

    private Conference getConferenceFromRequest(HttpServletRequest request) {
        Conference conference = null;
        if (request.getSession() != null) {
            long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
            conference = conferenceService.getById(conferenceId);
        }
        return conference;
    }

    private void tryToApproveLecture(HttpServletRequest request, long lectureId, Conference conference) {
        if (lectureService.approve(lectureId)) {
            conference = conferenceService.getById(lectureId);
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
            LOGGER.info("Lecture was approved");
        } else {
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
            request.setAttribute(AttributesManager.getProperty("not.approved"), true);
            LOGGER.debug("Lecture wasn't approved");
        }

    }
}


//
//    @Override
//    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
//        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
//        Conference conference = conferenceService.getById(conferenceId);
//        if (lecturesCapacityEnough(conference)) {
//            if (lectureService.approve(lectureId)) {
//                conference = conferenceService.getById(conferenceId);
//                request.setAttribute(AttributesManager.getProperty("conference"), conference);
//                LOGGER.info("Lecture was approved");
//            } else {
//                request.setAttribute(AttributesManager.getProperty("not.approved"), true);
//                LOGGER.debug("Lecture wasn't approved");
//            }
//        } else {
//            request.setAttribute(AttributesManager.getProperty("not.enough.lectures.capacity"), true);
//            LOGGER.debug("Lecture wasn't approved");
//        }
//        return PathManager.getProperty("path.page.conference");
//    }
//    private boolean lecturesCapacityEnough(Conference conference){
//        return conference.getConferenceLectures().size()<conference.getLecturesCapacity();
//    }