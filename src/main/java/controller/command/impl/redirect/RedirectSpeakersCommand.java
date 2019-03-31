package controller.command.impl.redirect;

import controller.command.Command;
import model.entity.Speaker;
import model.service.SpeakerService;
import model.service.impl.DefaultSpeakerService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.SpeakerValidation;
import model.validation.impl.DefaultSpeakerValidation;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class RedirectSpeakersCommand implements Command {
    private static SpeakerService speakerService;


    public RedirectSpeakersCommand() {
        speakerService = DefaultSpeakerService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Speaker> speakers = speakerService.getAll();
        request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
        return PathManager.getProperty("path.page.speakers");
    }
}


