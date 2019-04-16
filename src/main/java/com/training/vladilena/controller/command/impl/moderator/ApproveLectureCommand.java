package com.training.vladilena.controller.command.impl.moderator;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.LectureService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.model.service.impl.DefaultLectureService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code ApproveLectureCommand} class implements {@link Command} and is used for
 * changing {@code isApproved} status of {@link Lecture} to true by Moderator
 *
 * @author Vladlena Ushakova
 */
public class ApproveLectureCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ApproveLectureCommand.class);
    private static LectureService lectureService;
    private static ConferenceService conferenceService;

    public ApproveLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
        Conference conference = getConference(lectureId);

        if (lecturesCapacityEnough(conference)) {
            LOGGER.debug("Lectures capacity is enough for this conference");
            tryToApproveLecture(request, lectureId, conference);
        } else {
            request.setAttribute(AttributesManager.getProperty("not.enough.lectures.capacity"), true);
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
            LOGGER.debug("Lecture wasn't approved. Not enough capacity");
        }
        return PathManager.getProperty("path.page.conference");
    }

    private boolean lecturesCapacityEnough(Conference conference) {
        return conference.getConferenceLectures().size() < conference.getLecturesCapacity();
    }

    private Conference getConference(long lectureId) {
        Conference conference = null;
        Lecture lecture = lectureService.getById(lectureId);
        if (lecture != null) {
            conference = conferenceService.getById(lecture.getMainConference().getId());
        }
        return conference;
    }

    private void tryToApproveLecture(HttpServletRequest request, long lectureId, Conference conference) {
        if (lectureService.approve(lectureId)) {
            conference = conferenceService.getById(conference.getId());
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
            LOGGER.info("Lecture was approved");
        } else {
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
            request.setAttribute(AttributesManager.getProperty("not.approved"), true);
            LOGGER.debug("Lecture wasn't approved");
        }
    }
}
