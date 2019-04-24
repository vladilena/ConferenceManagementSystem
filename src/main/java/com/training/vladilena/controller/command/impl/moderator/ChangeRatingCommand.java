package com.training.vladilena.controller.command.impl.moderator;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.impl.DefaultSpeakerService;
import com.training.vladilena.model.validation.SpeakerValidation;
import com.training.vladilena.model.validation.impl.DefaultSpeakerValidation;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * The {@code ChangeRatingCommand} class implements {@link Command}
 * and is used for editing the {@link Speaker}'s rating by Moderator
 *
 * @author Vladlena Ushakova
 */
public class ChangeRatingCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeRatingCommand.class);
    private static SpeakerService speakerService;
    private static SpeakerValidation validation;

    public ChangeRatingCommand() {
        speakerService = DefaultSpeakerService.getInstance();
        validation = DefaultSpeakerValidation.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        double newRating = Double.valueOf(request.getParameter(AttributesManager.getProperty("rating")));
        long speakerId = Long.valueOf(request.getParameter(AttributesManager.getProperty("speaker.id")));
        List<Speaker> speakers = speakerService.getAll();

        if (ratingValid(newRating)) {
            LOGGER.debug("Validation was succeed");
            tryToChangeRating(newRating, speakerId, request, speakers);
        } else {
            LOGGER.debug("Invalid input for rating: " + newRating);
            request.setAttribute(AttributesManager.getProperty("invalid.input.rating"), true);
            request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
        }
        return PathManager.getProperty("path.page.speakers");
    }

    private boolean ratingValid(double rating) {
        return validation.ratingValid(rating);
    }

    private void tryToChangeRating(double newRating, long speakerId, HttpServletRequest request, List<Speaker> speakers) {
        if (speakerService.changeRating(newRating, speakerId)) {
            LOGGER.info("Rating was changed");
            speakers = speakerService.getAll();
            request.setAttribute(AttributesManager.getProperty("success.change"), true);
            request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
        } else {
            request.setAttribute(AttributesManager.getProperty("not.change"), true);
            request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
            LOGGER.debug("Rating wasn't changed");
        }
    }
}

