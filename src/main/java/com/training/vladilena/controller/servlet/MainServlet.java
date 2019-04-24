package com.training.vladilena.controller.servlet;

import com.training.vladilena.controller.command.Command;
import com.training.vladilena.controller.command.CommandFactory;
import com.training.vladilena.model.exceptions.PageNotFoundException;
import com.training.vladilena.model.exceptions.PermissionErrorException;
import com.training.vladilena.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * The {@code MainServlet} class extends {@link HttpServlet} is a controller that receives
 * and forwards all requests or redirect to the desired page
 *
 * @author Vladlena Ushakova
 */
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private static final String PAGE_SUFFIX = "jsp";

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processUser(req, resp);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processUser(req, resp);
    }

    private void processUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            tryProcessRequest(req, resp);
        } catch (PageNotFoundException e) {
            LOGGER.error("Threw a PageNotFoundException, full stack trace follows: ", e);
            forwardToNotFoundErrorPage(req, resp);
        } catch (PermissionErrorException e) {
            LOGGER.error("Threw a PermissionErrorException, full stack trace follows: ", e);
            forwardToPermissionErrorPage(req, resp);
        } catch (Exception e) {
            LOGGER.error("Threw an Exception, full stack trace follows: ", e);
            forwardToServerErrorPage(req, resp);
        }
    }

    private void tryProcessRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (!response.isCommitted()) {
            String page = executeCommand(request, response);
            resolvePagePath(page, request, response);
        }
    }

    private String executeCommand(HttpServletRequest request, HttpServletResponse response) {
        CommandFactory commandFactory = CommandFactory.getInstance();
        Command command = commandFactory.getCommand(request);
        return command.execute(request, response);
    }


    private void resolvePagePath(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            if (page.contains(PAGE_SUFFIX))
                forwardToPage(page, request, response);
            else {
                redirectToPage(page, request, response);
            }
        } catch (PageNotFoundException e) {
            LOGGER.error("Threw a PageNotFoundException, stack trace: " + e);
            forwardToNotFoundErrorPage(request, response);
        }
    }

    private void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.debug("Forward to: " + page);
        request.getRequestDispatcher(page).forward(request, response);
    }

    private void redirectToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        LOGGER.debug("Received path: " + page);
        LOGGER.debug("Redirect to " + request.getContextPath() + page);
        response.sendRedirect(request.getContextPath() + page);
    }

    private void forwardToNotFoundErrorPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = PathManager.getProperty("path.page.error.404");
        forwardToPage(page, request, response);
    }

    private void forwardToServerErrorPage(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String page = PathManager.getProperty("path.page.error.500");
        forwardToPage(page, request, response);
    }

    private void forwardToPermissionErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = PathManager.getProperty("path.page.error.perm");
        forwardToPage(page, request, response);
    }
}


