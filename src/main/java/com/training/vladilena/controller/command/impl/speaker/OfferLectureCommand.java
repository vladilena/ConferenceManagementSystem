package com.training.vladilena.controller.command.impl.speaker;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.GenerateLecture;
import com.training.vladilena.controller.command.GenerateUser;
import com.training.vladilena.model.dto.InvalidData;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.service.LectureService;
import com.training.vladilena.model.service.impl.DefaultLectureService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code OfferLectureCommand} class implements {@link Command}
 * and is used for offer the new {@link Lecture} by Speaker or Moderator
 *
 * @author Vladlena Ushakova
 */
public class OfferLectureCommand implements Command, GenerateLecture, GenerateUser {
    private static final Logger LOGGER = LogManager.getLogger(OfferLectureCommand.class);

    private static LectureService lectureService;

    public OfferLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        User user = getUserFromSession(request);
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
