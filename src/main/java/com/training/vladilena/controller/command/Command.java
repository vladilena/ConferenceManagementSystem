package com.training.vladilena.controller.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * The {@code Command} interface is used for command pattern
 *
 * @author Vladlena Ushakova
 */
public interface Command {
    /**
     * Method execute the necessary business logic and services
     *
     * @param request the {@link HttpServletRequest} which will be filled with all necessary attributes
     * @param response the {@link HttpServletResponse}
     * @return returns the path to the page that is ready to forward
     */
    String execute(HttpServletRequest request, HttpServletResponse response);
}
