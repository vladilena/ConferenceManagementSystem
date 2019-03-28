package controller.command;

import controller.command.impl.*;
import controller.command.impl.auth.LoginCommand;
import controller.command.impl.auth.LogoutCommand;
import controller.command.impl.auth.RegistrationCommand;
import controller.command.impl.redirect.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
  //  private static final Logger logger = LogManager.getLogger(CommandFactory.class);

    private static volatile CommandFactory factory;

    private final Map<String, Command> commands;

    private CommandFactory() {
     //   logger.debug("Initialization of commands hashmap");
        commands = new HashMap<>();
        commands.put("main", new MainCommand());
        commands.put("redirect_registration", new RedirectRegistrationCommand());
        commands.put("redirect_login", new RedirectLoginCommand());
        commands.put("login", new LoginCommand());
        commands.put("register", new RegistrationCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("redirect_conference", new RedirectConferenceCommand());
        //TODO//CHANGE!!
     //   commands.put("find_by_time_period", new FindConferencesByTimeCommand());
        commands.put("redirect_profile", new RedirectProfileCommand());
        commands.put("redirect_speakers", new RedirectSpeakersCommand());
        commands.put("redirect_create_conference", new RedirectCreateConferenceCommand());
        commands.put("redirect_offer_lecture", new RedirectOfferLecture());
        commands.put("redirect_change_conference", new RedirectChangeConference());
        commands.put("redirect_change_lecture", new RedirectChangeLecture());
        commands.put("offer_lecture", new OfferLectureCommand());
        commands.put("create_conference", new CreateConferenceCommand());
        commands.put("change_rating", new ChangeRatingCommand());
        commands.put("participate", new SubscribeOnConference());
//        commands.put("change_conference", new );

//        commands.put("send_invitations", new );
//        commands.put("approve", new );
//        commands.put("change_lecture", new );

    }

    public static CommandFactory getInstance() {
        CommandFactory localInstance = factory;
        if (localInstance == null) {
            synchronized (CommandFactory.class) {
                localInstance = factory;
                if (localInstance == null) {
                    factory = new CommandFactory();
                  //  logger.debug("Create first CommandFactory instance");
                }
            }
        }
      //  logger.debug("Return CommandFactory instance");
        return factory;
    }


    public Command getCommand(HttpServletRequest request) {
        Command current = new EmptyCommand();
        String action = request.getParameter("action");
        if (isInvalidCommand(action)) {
           // logger.info("There is no such command" + request.getMethod() + request.getRequestURI());
            return current;
        }
       // logger.info("There is such command" + action);
        current = commands.getOrDefault(action, current);
        return current;
    }

    private boolean isInvalidCommand(String action) {
        return action == null || action.isEmpty();
    }

}


