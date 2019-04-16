package com.training.vladilena.controller.command.impl.redirect;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.util.AttributesManager;
import com.training.vladilena.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code RedirectOfferLectureCommand} class implements {@link Command}
 * and is used for redirect to the offer lecture page
 *
 * @author Vladlena Ushakova
 */
public class RedirectOfferLectureCommand implements Command {
    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        return PathManager.getProperty("path.page.offer");
    }
}


