package controller.command;

import controller.command.impl.*;
import controller.command.impl.auth.LoginCommand;
import controller.command.impl.auth.LogoutCommand;
import controller.command.impl.auth.RegistrationCommand;
import controller.command.impl.moderator.ApproveLectureCommand;
import controller.command.impl.moderator.ChangeRatingCommand;
import controller.command.impl.moderator.CreateConferenceCommand;
import controller.command.impl.redirect.*;
import controller.command.impl.speaker.OfferLectureCommand;
import controller.command.impl.user.ChangeLanguageCommand;
import controller.command.impl.user.SubscribeOnConference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);

    private static volatile CommandFactory factory;

    private final Map<String, Command> commands;

    private CommandFactory() {
        LOGGER.debug("Initialization of commands hashmap");
        commands = new HashMap<>();
        commands.put("main", new MainCommand());
        commands.put("redirect_registration", new RedirectRegistrationCommand());
        commands.put("redirect_login", new RedirectLoginCommand());
        commands.put("login", new LoginCommand());
        commands.put("register", new RegistrationCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("redirect_conference", new RedirectConferenceCommand());
        commands.put("redirect_profile", new RedirectProfileCommand());
        commands.put("redirect_speakers", new RedirectSpeakersCommand());
        commands.put("redirect_create_conference", new RedirectCreateConferenceCommand());
        commands.put("redirect_offer_lecture", new RedirectOfferLectureCommand());
        commands.put("redirect_change_conference", new RedirectChangeConference());
        commands.put("redirect_change_lecture", new RedirectChangeLecture());
        commands.put("offer_lecture", new OfferLectureCommand());
        commands.put("create_conference", new CreateConferenceCommand());
        commands.put("change_rating", new ChangeRatingCommand());
        commands.put("participate", new SubscribeOnConference());
        // commands.put("change_conference", new );
        // commands.put("send_invitations", new );
        commands.put("approve", new ApproveLectureCommand());
        // commands.put("change_lecture", new );
        commands.put("change_language", new ChangeLanguageCommand());

    }

    public static CommandFactory getInstance() {
        CommandFactory localInstance = factory;
        if (localInstance == null) {
            synchronized (CommandFactory.class) {
                localInstance = factory;
                if (localInstance == null) {
                    factory = new CommandFactory();
                    LOGGER.debug("Create first CommandFactory instance");
                }
            }
        }
        LOGGER.debug("Return CommandFactory instance");
        return factory;
    }


    public Command getCommand(HttpServletRequest request) {
        Command current = new EmptyCommand();
        String action = request.getParameter("action");
        if (isInvalidCommand(action)) {
            LOGGER.info("There is no such command " + request.getMethod() +"  "+ request.getRequestURI());
            return current;
        }
        LOGGER.info("There is such command " + action);
        current = commands.getOrDefault(action, current);
        return current;
    }


    private boolean isInvalidCommand(String action) {
        return action == null || action.isEmpty();
    }

}


