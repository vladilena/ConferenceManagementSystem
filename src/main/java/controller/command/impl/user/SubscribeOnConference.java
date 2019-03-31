package controller.command.impl.user;

import controller.command.Command;
import model.entity.Conference;
import model.entity.User;
import model.service.ConferenceService;
import model.service.UserService;
import model.service.impl.DefaultConferenceService;
import model.service.impl.DefaultUserService;
import model.util.AttributesManager;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeOnConference implements Command {
    private static final Logger LOGGER = LogManager.getLogger(SubscribeOnConference.class);
    private static UserService userService;
    private static ConferenceService conferenceService;

    public SubscribeOnConference() {
        userService = DefaultUserService.getInstance();
        conferenceService = DefaultConferenceService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = getUserFromRequest(request);
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

    private User getUserFromRequest(HttpServletRequest request) {
        User user = null;
        if (request.getSession() != null) {
            user = (User) request.getSession().getAttribute(AttributesManager.getProperty("user"));
        }
        return user;
    }

    private Conference getConferenceFromRequest(HttpServletRequest request) {
        long conferenceId = Long.valueOf(request.getSession().getAttribute(AttributesManager.getProperty("conference.id")).toString());
        Conference conference = conferenceService.getById(conferenceId);

        return conference;
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
