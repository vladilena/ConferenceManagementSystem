package controller.command.impl;

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

public class ChangeRatingCommand implements Command {
    private static SpeakerService speakerService;
    private static SpeakerValidation validation;

    public ChangeRatingCommand() {
        speakerService = DefaultSpeakerService.getInstance();
        validation = DefaultSpeakerValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
    int newRating =  Integer.valueOf(request.getParameter("rating"));
    long speakerId = Long.valueOf(request.getParameter("speaker_id"));
        if (validation.ratingValid(newRating)){
            //valid
            if(speakerService.changeRating(newRating, speakerId)){
                //success
                List<Speaker> speakers = speakerService.getAll();
                request.setAttribute(AttributesManager.getProperty("success.change"), true);
            request.setAttribute(AttributesManager.getProperty("speaker_list"), speakers);
            }else {
                request.setAttribute(AttributesManager.getProperty("not_change"), true);
            }
        }else {
            //invalid
            List<Speaker> speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("invalid.input.rating"), true);
            request.setAttribute(AttributesManager.getProperty("speaker_list"), speakers);
        }
        return PathManager.getProperty("path.page.speakers");
    }
}


