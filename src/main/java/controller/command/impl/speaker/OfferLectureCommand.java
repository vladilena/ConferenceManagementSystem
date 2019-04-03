package controller.command.impl.speaker;

import controller.command.Command;
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

public class OfferLectureCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(OfferLectureCommand.class);

    private static LectureService lectureService;
    private static LectureValidation validation;

    public OfferLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        validation = DefaultLectureValidation.getInstance();
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
            request.setAttribute(AttributesManager.getProperty("invalid.input.lecture"), true);
        }
        return PathManager.getProperty("path.page.offer");
    }

    private InvalidData inputChecked(Lecture lecture) {
        InvalidData.Builder builder = InvalidData.newBuilder(AttributesManager.getProperty("invalidData"));
        boolean invalidDataFlag = false;
        if (!validation.titleValid(lecture.getTitle())) {
            LOGGER.debug("Invalid title ukr: " + lecture.getTitle());
            builder.setInvalidTitleAttr();
            invalidDataFlag = true;
        }
        if (!validation.titleEnValid(lecture.getTitleEn())) {
            LOGGER.debug("Invalid title en: " + lecture.getTitleEn());
            builder.setInvalidTitleEnAttr();
            invalidDataFlag = true;
        }
        if (!validation.descriptionValid((lecture.getDescription()))) {
            LOGGER.debug("Invalid description ukr: " + lecture.getDescription());
            builder.setInvalidDescriptionAttr();
            invalidDataFlag = true;
        }
        if (!validation.descriptionEnValid((lecture.getDescriptionEn()))) {
            LOGGER.debug("Invalid description en: " + lecture.getDescriptionEn());
            builder.setInvalidDescriptionEnAttr();
            invalidDataFlag = true;
        }
        return invalidDataFlag ? builder.build() : null;
    }

    private Lecture getLectureFromRequest(HttpServletRequest request) {
        LOGGER.debug("Get lecture from request");
        Lecture lecture = new Lecture();
        lecture.setTitle(request.getParameter(AttributesManager.getProperty("title")));
        lecture.setTitleEn(request.getParameter(AttributesManager.getProperty("title.en")));
        lecture.setDescription(request.getParameter(AttributesManager.getProperty("description")));
        lecture.setDescriptionEn(request.getParameter(AttributesManager.getProperty("description.en")));
        return lecture;
    }

    private User getUserFromRequest(HttpServletRequest request) {
        User user = null;
        if (request.getSession() != null) {
            user = (User) request.getSession().getAttribute("user");
        }
        return user;
    }

    private void tryToCreateLecture(HttpServletRequest request, Lecture lecture, User user, long conferenceId) {
        if (lectureService.create(lecture, user, conferenceId)) {
            request.setAttribute(AttributesManager.getProperty("success.offer"), true);
            LOGGER.info("Lecture was created");
        } else {
            LOGGER.debug("A problem with creation was occurred");
            request.setAttribute(AttributesManager.getProperty("not.add.lecture"), true);
        }
    }
}
