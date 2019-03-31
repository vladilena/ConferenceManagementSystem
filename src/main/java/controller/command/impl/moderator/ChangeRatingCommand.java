package controller.command.impl.moderator;

import controller.command.Command;
import model.entity.Speaker;
import model.service.SpeakerService;
import model.service.impl.DefaultSpeakerService;
import model.util.AttributesManager;
import model.util.PathManager;
import model.validation.SpeakerValidation;
import model.validation.impl.DefaultSpeakerValidation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ChangeRatingCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeRatingCommand.class);
    private static SpeakerService speakerService;
    private static SpeakerValidation validation;

    public ChangeRatingCommand() {
        speakerService = DefaultSpeakerService.getInstance();
        validation = DefaultSpeakerValidation.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        int newRating = Integer.valueOf(request.getParameter(AttributesManager.getProperty("rating")));
        long speakerId = Long.valueOf(request.getParameter(AttributesManager.getProperty("speaker.id")));

        if (ratingValid(newRating)) {
            LOGGER.debug("Validation was succeed");
            tryToChangeRating(newRating, speakerId, request);
        } else {
            LOGGER.debug("Invalid input for rating: " + newRating);
            List<Speaker> speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("invalid.input.rating"), true);
            request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
        }

        return PathManager.getProperty("path.page.speakers");
    }

    private boolean ratingValid(int rating) {
        return validation.ratingValid(rating);
    }

    private void tryToChangeRating(int newRating, long speakerId, HttpServletRequest request) {
        if (speakerService.changeRating(newRating, speakerId)) {
            LOGGER.info("Rating was changed");
            List<Speaker> speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("success.change"), true);
            request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
        } else {
            request.setAttribute(AttributesManager.getProperty("not.change"), true);
            LOGGER.debug("Rating wasn't changed");
        }
    }

}

