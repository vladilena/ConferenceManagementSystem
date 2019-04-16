package com.training.vladilena.controller.command;

import com.training.vladilena.controller.command.impl.ChangeLanguageCommand;
import com.training.vladilena.controller.command.impl.EmptyCommand;
import com.training.vladilena.controller.command.impl.MainCommand;
import com.training.vladilena.controller.command.impl.auth.LoginCommand;
import com.training.vladilena.controller.command.impl.auth.LogoutCommand;
import com.training.vladilena.controller.command.impl.auth.RegistrationCommand;
import com.training.vladilena.controller.command.impl.moderator.*;
import com.training.vladilena.controller.command.impl.redirect.*;
import com.training.vladilena.controller.command.impl.speaker.GetBonusCommand;
import com.training.vladilena.controller.command.impl.speaker.OfferLectureCommand;
import com.training.vladilena.controller.command.impl.user.SubscribeOnConference;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
/**
 * The {@code CommandFactory} is a class which depending on the request parameter
 * generate the {@link Command} class instance to execute
 *
 * @author Ushakova Vladlena
 */
public class CommandFactory {
    private static final Logger LOGGER = LogManager.getLogger(CommandFactory.class);
    private static volatile CommandFactory factory;
    /**Map that stores the string representation of commands and their instances */
    private final Map<String, Command> commands;
    /**
     * Constructor which initialize {@code commands}
     */
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
        commands.put("redirect_change_conference", new RedirectChangeConferenceCommand());
        commands.put("redirect_change_lecture", new RedirectChangeLectureCommand());
        commands.put("offer_lecture", new OfferLectureCommand());
        commands.put("create_conference", new CreateConferenceCommand());
        commands.put("change_rating", new ChangeRatingCommand());
        commands.put("participate", new SubscribeOnConference());
        commands.put("change_conference", new ChangeConferenceCommand());
        commands.put("redirect_create_report", new RedirectCreateReportCommand());
        commands.put("create_report", new CreateReportCommand());
        commands.put("approve", new ApproveLectureCommand());
        commands.put("change_lecture", new ChangeLectureCommand());
        commands.put("delete_lecture", new DeleteLectureCommand());
        commands.put("change_language", new ChangeLanguageCommand());
        commands.put("delete_conference", new DeleteConferenceCommand());
        commands.put("get_bonus", new GetBonusCommand());
    }
    /**
     * @return always return the same {@link CommandFactory} instance
     */
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
            LOGGER.info("Command isn't found: " + request.getMethod() + "  " + request.getRequestURI());
            return current;
        }
        LOGGER.info("Found command: " + action);
        current = commands.getOrDefault(action, current);
        return current;
    }

    private boolean isInvalidCommand(String action) {
        return action == null || action.isEmpty();
    }

}


