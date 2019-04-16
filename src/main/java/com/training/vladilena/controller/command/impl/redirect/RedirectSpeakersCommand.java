package com.training.vladilena.controller.command.impl.redirect;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.model.entity.Speaker;
import com.training.vladilena.model.service.SpeakerService;
import com.training.vladilena.model.service.impl.DefaultSpeakerService;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
/**
 * The {@code RedirectSpeakersCommand} class implements {@link Command}
 * and is used for redirect to the speakers list page and  fill it with data
 *
 * @author Vladlena Ushakova
 */
public class RedirectSpeakersCommand implements Command {
    private static SpeakerService speakerService;

    public RedirectSpeakersCommand() {
        speakerService = DefaultSpeakerService.getInstance();
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<Speaker> speakers = speakerService.getAll();
        request.setAttribute(AttributesManager.getProperty("speaker.list"), speakers);
        return PathManager.getProperty("path.page.speakers");
    }
}


