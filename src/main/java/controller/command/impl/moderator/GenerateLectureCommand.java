package controller.command.impl.moderator;

import model.dto.InvalidData;
import model.entity.Lecture;
import model.entity.User;
import model.util.AttributesManager;
import model.validation.LectureValidation;
import model.validation.impl.DefaultLectureValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public interface GenerateLectureCommand {
    Logger LOGGER = LogManager.getLogger(GenerateLectureCommand.class);
    LectureValidation validation = DefaultLectureValidation.getInstance();

    default InvalidData inputChecked(Lecture lecture) {
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
        if(!validation.startTimeValid(lecture.getStartTime(), lecture.getMainConference().getDateTime())){
            LOGGER.debug("Invalid start time: "+lecture.getStartTime());
            builder.setInvalidLectureStartTimeAttr();
            invalidDataFlag = true;
        }



        return invalidDataFlag ? builder.build() : null;
    }

    default Lecture getLectureFromRequest(HttpServletRequest request) {
        LOGGER.debug("Get lecture from request");
        Lecture lecture = new Lecture();
        lecture.setTitle(request.getParameter(AttributesManager.getProperty("title")));
        lecture.setTitleEn(request.getParameter(AttributesManager.getProperty("title.en")));
        lecture.setDescription(request.getParameter(AttributesManager.getProperty("description")));
        lecture.setDescriptionEn(request.getParameter(AttributesManager.getProperty("description.en")));
        return lecture;
    }

    default User getUserFromRequest(HttpServletRequest request) {
        User user = null;
        if (request.getSession() != null) {
            user = (User) request.getSession().getAttribute("user");
        }
        return user;
    }
}
