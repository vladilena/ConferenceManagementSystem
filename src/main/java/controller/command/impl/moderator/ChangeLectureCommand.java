package controller.command.impl.moderator;

import controller.command.Command;
import model.dto.InvalidData;
import model.entity.Conference;
import model.entity.Lecture;
import model.entity.Speaker;
import model.entity.User;
import model.service.ConferenceService;
import model.service.LectureService;
import model.service.SpeakerService;
import model.service.impl.DefaultConferenceService;
import model.service.impl.DefaultLectureService;
import model.service.impl.DefaultSpeakerService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.util.List;

public class ChangeLectureCommand implements Command, GenerateLectureCommand {
    private static LectureService lectureService;
    private static SpeakerService speakerService;
    private static ConferenceService conferenceService;

    public ChangeLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        speakerService = DefaultSpeakerService.getInstance();
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Lecture lecture = getLecture(request);

//TODO add validation for new fields: dateTime, ...

        InvalidData invalidData = inputChecked(lecture);

        if (invalidData == null) {
            LOGGER.debug("Changing was succeed");
            tryToUpdateLecture(request, lecture);
        } else {
            LOGGER.debug("Validation error");
            request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
            List<Speaker> speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("speakers"), speakers);
            request.setAttribute(AttributesManager.getProperty("invalid.input.lecture"), invalidData);
        }
        return PathManager.getProperty("path.page.change.lecture");
    }


    private void tryToUpdateLecture(HttpServletRequest request, Lecture lecture) {
        if (lectureService.update(lecture)) {
            request.setAttribute(AttributesManager.getProperty("success.update.lecture"), true);
            LOGGER.info("Lecture was updated");
        } else {
            LOGGER.debug("Lecture wasn't updated");
            request.setAttribute(AttributesManager.getProperty("not.update.lecture"), true);
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


