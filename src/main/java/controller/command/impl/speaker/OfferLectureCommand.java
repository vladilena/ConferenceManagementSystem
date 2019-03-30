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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OfferLectureCommand implements Command {
    private static LectureService lectureService;
    private static LectureValidation validation;

    public OfferLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        validation = DefaultLectureValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
      //  long conferenceId = Long.valueOf(request.getParameter("conference_id"));

        long conferenceId = Long.valueOf(request.getSession().getAttribute("conference_id").toString());
        request.getSession().removeAttribute("conference_id");

        User user = (User) request.getSession().getAttribute("user");
        Lecture lecture = getLectureFromRequest(request);

        InvalidData invalidData = inputChecked(lecture);

        if(invalidData == null){
            //valid
            if(lectureService.create(lecture, user, conferenceId)){
                request.setAttribute(AttributesManager.getProperty("success.offer"), true);
            }else {
                //not add
                request.setAttribute(AttributesManager.getProperty("not.add.lecture"), true);
            }
            request.setAttribute(AttributesManager.getProperty("invalid.input.lecture"), true);
        }

        return PathManager.getProperty("path.page.offer");
    }
    private InvalidData inputChecked(Lecture lecture){
        InvalidData.Builder builder = InvalidData.newBuilder("invalidData");
        boolean invalidDataFlag = false;
        if(!validation.titleValid(lecture.getTitle())){
            builder.setInvalidTitleAttr();
            invalidDataFlag = true;
        }
        if(!validation.titleEnValid(lecture.getTitleEn())){
            builder.setInvalidTitleEnAttr();
            invalidDataFlag = true;
        }
        if(!validation.descriptionValid((lecture.getDescription()))){
            builder.setInvalidDescriptionAttr();
            invalidDataFlag = true;
        }
        if(!validation.descriptionEnValid((lecture.getDescriptionEn()))){
            builder.setInvalidDescriptionEnAttr();
            invalidDataFlag = true;
        }
        return invalidDataFlag ? builder.build() : null;
    }
    private Lecture getLectureFromRequest(HttpServletRequest request){
        Lecture lecture = new Lecture();
        lecture.setTitle(request.getParameter("title"));
        lecture.setTitleEn(request.getParameter("title_en"));
        lecture.setDescription(request.getParameter("description"));
        lecture.setDescriptionEn(request.getParameter("description_en"));
        return lecture;
    }
}


