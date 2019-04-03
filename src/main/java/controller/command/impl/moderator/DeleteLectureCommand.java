package controller.command.impl.moderator;

import controller.command.Command;
import model.entity.Lecture;
import model.service.LectureService;
import model.service.impl.DefaultLectureService;
import model.util.AttributesManager;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteLectureCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(DeleteLectureCommand.class);
    private static LectureService lectureService;

    public DeleteLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
        if (lectureService.delete(lectureId)) {
            LOGGER.debug("Lecture was deleted succeed");
            return PathManager.getProperty("redirect.page.main");
        }
        LOGGER.debug("Delete wasn't succeed");
        Lecture lecture = lectureService.getById(lectureId);
        request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
        return PathManager.getProperty("path.page.change.lecture");
    }
}


