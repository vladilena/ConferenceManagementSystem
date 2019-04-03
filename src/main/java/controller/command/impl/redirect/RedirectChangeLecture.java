package controller.command.impl.redirect;

import controller.command.Command;
import model.entity.Lecture;
import model.entity.Speaker;
import model.service.LectureService;
import model.service.SpeakerService;
import model.service.impl.DefaultLectureService;
import model.service.impl.DefaultSpeakerService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class RedirectChangeLecture implements Command {
    private static LectureService lectureService;
    private static SpeakerService speakerService;

    public RedirectChangeLecture(){
        lectureService = DefaultLectureService.getInstance();
        speakerService = DefaultSpeakerService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
        //long conferenceId = Long.valueOf(request.getParameter(AttributesManager.getProperty("conference.id")));
        Lecture lecture = lectureService.getById(lectureId);
        List<Speaker> speakers = speakerService.getAll();
        request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
        request.setAttribute(AttributesManager.getProperty("speakers"), speakers);
        return PathManager.getProperty("path.page.change.lecture");
    }
}


