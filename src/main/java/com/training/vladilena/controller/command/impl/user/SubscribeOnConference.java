package com.training.vladilena.controller.command.impl.user;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.GenerateUser;
import com.training.vladilena.model.entity.Conference;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.service.ConferenceService;
import com.training.vladilena.model.service.UserService;
import com.training.vladilena.model.service.impl.DefaultConferenceService;
import com.training.vladilena.model.service.impl.DefaultUserService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code SubscribeOnConference} class implements {@link Command}
 * and is used for subscribe {@link User} on {@link Conference}
 *
 * @author Vladlena Ushakova
 */
public class SubscribeOnConference implements Command, GenerateUser {
    private static final Logger LOGGER = LogManager.getLogger(SubscribeOnConference.class);
    private static UserService userService;
    private static ConferenceService conferenceService;

    public SubscribeOnConference() {
        userService = DefaultUserService.getInstance();
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = getUserFromSession(request);
        Conference conference = getConferenceFromRequest(request);

        if (enoughPlaces(conference)) {
            tryToSubscribe(user, conference, request);
        } else {
            LOGGER.debug("Not enough free places");
            request.setAttribute(AttributesManager.getProperty("not.enough.places"), true);
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
        }
        return PathManager.getProperty("path.page.conference");
    }

    private Conference getConferenceFromRequest(HttpServletRequest request) {
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        return conferenceService.getById(conferenceId);
    }

    private boolean enoughPlaces(Conference conference) {
        return conference.getRegisteredParticipants().size() < conference.getPlaceCapacity();
    }

    private void tryToSubscribe(User user, Conference conference, HttpServletRequest request) {
        if (userService.subscribeOnConference(user.getId(), conference.getId())) {
            LOGGER.info("Operation was succeed");
            request.setAttribute(AttributesManager.getProperty("success.subscribe"), true);
            request.setAttribute(AttributesManager.getProperty("conference"), conference);

        } else {
            LOGGER.debug("Operation was failed");
            request.setAttribute(AttributesManager.getProperty("already.subscribed"), true);
            request.setAttribute(AttributesManager.getProperty("conference"), conference);
        }
    }
}
