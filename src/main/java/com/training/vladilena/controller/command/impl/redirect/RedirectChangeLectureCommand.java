package com.training.vladilena.controller.command.impl.redirect;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.Lecture;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.service.LectureService;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.impl.DefaultLectureService;
import com.training.vladilena.model.service.impl.DefaultSpeakerService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The {@code RedirectChangeLectureCommand} class implements {@link Command}
 * and is used for redirect to the lecture page and fill it with data
 *
 * @author Vladlena Ushakova
 */
public class RedirectChangeLectureCommand implements Command {
    private static LectureService lectureService;
    private static SpeakerService speakerService;

    public RedirectChangeLectureCommand() {
        lectureService = DefaultLectureService.getInstance();
        speakerService = DefaultSpeakerService.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        long lectureId = Long.valueOf(request.getParameter(AttributesManager.getProperty("lecture.id")));
        Lecture lecture = lectureService.getById(lectureId);
        List<Speaker> speakers = speakerService.getAll();
        request.setAttribute(AttributesManager.getProperty("lecture"), lecture);
        request.setAttribute(AttributesManager.getProperty("speakers"), speakers);
        return PathManager.getProperty("path.page.change.lecture");
    }
}


