package controller.command.impl.redirect;

import controller.command.Command;
import model.entity.Speaker;
import model.entity.User;
import model.service.SpeakerService;
import model.service.UserService;
import model.service.impl.DefaultSpeakerService;
import model.service.impl.DefaultUserService;
import model.util.AttributesManager;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.MarkerManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectProfileCommand implements Command {
    private final static Logger LOGGER = LogManager.getLogger(RedirectProfileCommand.class);
    private static SpeakerService speakerService;

    public RedirectProfileCommand() {
        speakerService = DefaultSpeakerService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesManager.getProperty("user"));
        Speaker speaker = speakerService.getById(user.getId());
        if(speaker!=null){
            LOGGER.debug("Speaker exists");
            request.setAttribute(AttributesManager.getProperty("speaker"), speaker);
        }else {
            LOGGER.debug("Speaker not exists");
        }



        return PathManager.getProperty("path.page.profile");
    }
}


