package com.training.vladilena.controller.command.impl.redirect;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.GenerateUser;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.impl.DefaultSpeakerService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The {@code RedirectProfileCommand} class implements {@link Command}
 * and is used for redirect to the profile page and fill it with {@link Speaker} data
 *
 * @author Vladlena Ushakova
 */
public class RedirectProfileCommand implements Command, GenerateUser {
    private final static Logger LOGGER = LogManager.getLogger(RedirectProfileCommand.class);
    private static SpeakerService speakerService;

    public RedirectProfileCommand() {
        speakerService = DefaultSpeakerService.getInstance();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = getUserFromSession(request);
        Speaker speaker = speakerService.getById(user.getId());
        if (speaker != null) {
            LOGGER.debug("Speaker exists");
            request.setAttribute(AttributesManager.getProperty("speaker"), speaker);
        } else {
            LOGGER.debug("Speaker not exists");
            request.setAttribute(AttributesManager.getProperty("not.exist.speaker"), true);
        }
        return PathManager.getProperty("path.page.profile");
    }
}


