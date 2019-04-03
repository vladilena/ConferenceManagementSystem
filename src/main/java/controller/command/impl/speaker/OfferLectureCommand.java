package controller.command.impl.speaker;

import controller.command.Command;
import controller.command.impl.moderator.GenerateLectureCommand;
import model.dto.InvalidData;
import model.entity.Lecture;
import model.entity.User;
import model.service.LectureService;
import model.service.impl.DefaultLectureService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.LectureValidation;
import model.validation.impl.DefaultLectureValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OfferLectureCommand implements Command, GenerateLectureCommand {
    private static final Logger LOGGER = LogManager.getLogger(OfferLectureCommand.class);

    private static LectureService lectureService;

    public OfferLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        User user = getUserFromRequest(request);
        Lecture lecture = getLectureFromRequest(request);

        InvalidData invalidData = inputChecked(lecture);


        if (invalidData == null) {
            LOGGER.debug("Validation was succeed");
            tryToCreateLecture(request, lecture, user, conferenceId);
        } else {
            LOGGER.debug("Validation error");
            request.setAttribute(AttributesManager.getProperty("invalid.lecture"), lecture);
            request.setAttribute(AttributesManager.getProperty("invalid.input.lecture"), invalidData);
        }
        return PathManager.getProperty("path.page.offer");
    }

    private void tryToCreateLecture(HttpServletRequest request, Lecture lecture, User user, long conferenceId) {
        if (lectureService.create(lecture, conferenceId)) {
            request.setAttribute(AttributesManager.getProperty("success.offer"), true);
            LOGGER.info("Lecture was created");
        } else {
            LOGGER.debug("A problem with creation was occurred");
            request.setAttribute(AttributesManager.getProperty("not.add.lecture"), true);
        }
    }
}
