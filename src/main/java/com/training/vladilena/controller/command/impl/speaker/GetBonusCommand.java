package com.training.vladilena.controller.command.impl.speaker;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.impl.DefaultSpeakerService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code GetBonusCommand} class implements {@link Command}
 * and is used for changing the {@link Speaker}'s bonus to zero by Speaker and transfer it
 *
 * @author Vladlena Ushakova
 */
public class GetBonusCommand implements Command {
    private static final Logger LOGGER = LogManager.getLogger(GetBonusCommand.class);
    private static SpeakerService speakerService;

    public GetBonusCommand() {
        speakerService = DefaultSpeakerService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) request.getSession().getAttribute(AttributesManager.getProperty("user"));

        if(speakerService.getBonus(user.getId())){
            LOGGER.debug("Bonus was zeroed");
            request.setAttribute(AttributesManager.getProperty("speaker"),  speakerService.getById(user.getId()));
            request.setAttribute(AttributesManager.getProperty("bonus.transfered"), true);
        }else {
            LOGGER.debug("Bonus wasn't updated");
            request.setAttribute(AttributesManager.getProperty("bonus.not.transfered"), true);
        }
        return PathManager.getProperty("path.page.profile");
    }
}


