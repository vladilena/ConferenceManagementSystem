package com.training.vladilena.controller.command.impl.moderator;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.GenerateLecture;
import com.training.vladilena.model.dto.InvalidData;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.LectureService;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.model.service.impl.DefaultLectureService;
import com.training.vladilena.model.service.impl.DefaultSpeakerService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;
/**
 * The {@code ChangeLectureCommand} class implements {@link Command}, {@link GenerateLecture}
 * and is used for editing the {@link Lecture} by Moderator
 *
 * @author Vladlena Ushakova
 */
public class ChangeLectureCommand implements Command, GenerateLecture {
    private static LectureService lectureService;
    private static SpeakerService speakerService;
    private static ConferenceService conferenceService;

    public ChangeLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        speakerService = DefaultSpeakerService.getInstance();
        conferenceService = DefaultConferenceService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Lecture lecture = getLecture(request);
        InvalidData invalidData = inputChecked(lecture);

        if (invalidData == null) {
            LOGGER.debug("Changing was succeed");
            tryToUpdateLecture(request, lecture);
        } else {
            LOGGER.debug("Validation error");
            request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
            List<Speaker> speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("speakers"), speakers);
            request.setAttribute(AttributesManager.getProperty("invalid.data"), invalidData);
        }
        return PathManager.getProperty("path.page.change.lecture");
    }

    private void tryToUpdateLecture(HttpServletRequest request, Lecture lecture) {
        if (lectureService.update(lecture)) {
            request.setAttribute(AttributesManager.getProperty("success.update.lecture"), true);
            List<Speaker> speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("speakers"), speakers);
            request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
            LOGGER.info("Lecture was updated");
        } else {
            LOGGER.debug("Lecture wasn't updated");
            request.setAttribute(AttributesManager.getProperty("not.update.lecture"), true);
            List<Speaker> speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("speakers"), speakers);
            request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
        }
    }

    private Lecture getLecture(HttpServletRequest request) {
        Lecture lecture = getLectureFromRequest(request);
        lecture.setId(Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id"))));
        lecture.setStartTime(LocalDateTime.parse(request.getParameter(AttributesManager.getProperty("start.time"))));
        Speaker mainSpeaker = speakerService.getById(Long.valueOf(request.getParameter(AttributesManager.getProperty("speaker.id"))));
        lecture.setMainSpeaker(mainSpeaker);
        Conference conference = conferenceService.getById(Long.valueOf(request.getParameter(AttributesManager.getProperty("conference.id"))));
        lecture.setMainConference(conference);
        return lecture;
    }
}


