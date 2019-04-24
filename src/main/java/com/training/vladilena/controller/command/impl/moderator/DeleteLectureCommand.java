package com.training.vladilena.controller.command.impl.moderator;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.service.LectureService;
import com.training.vladilena.model.service.impl.DefaultLectureService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code DeleteLectureCommand} class implements {@link Command}
 * and is used for deleting the {@link Lecture} from the database by Moderator
 *
 * @author Vladlena Ushakova
 */
public class DeleteLectureCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(DeleteLectureCommand.class);
    private static LectureService lectureService;

    public DeleteLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
        Lecture lecture = lectureService.getById(lectureId);
        if (lectureService.delete(lectureId)) {
            LOGGER.debug("Lecture was deleted succeed");
            return PathManager.getProperty("redirect.page.main");
        } else {
            LOGGER.debug("Delete wasn't succeed");
            request.setAttribute(AttributesManager.getProperty("lect.not.delete"), true);
            request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
            return PathManager.getProperty("path.page.change.lecture");
        }
    }
}


