package controller;

import controller.command.Command;
import controller.command.CommandFactory;
import model.exceptions.PageNotFoundException;
import model.util.PathManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private static final String PAGE_REDIRECT = "redirect:";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processUser(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processUser(req, resp);
    }

    private void processUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            tryProcessRequest(req, resp);
        } catch (PageNotFoundException e) {
            LOGGER.debug("Threw a PageNotFoundException, full stack trace follows: ", e);
            forwardToNotFoundErrorPage(req, resp);
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
            if (page.contains(PAGE_REDIRECT))
                redirectToPage(page, request, response);
            else {
                forwardToPage(page, request, response);
            }
        } catch (PageNotFoundException e) {
            LOGGER.error("Threw a PageNotFoundException, stack trace: " + e);
           // forwardToNotFoundErrorPage(request, response);
        }
    }

    private void forwardToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        LOGGER.info("Forward to: " + page);
        request.getRequestDispatcher(page).forward(request, response);

    }

    private void redirectToPage(String page, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        LOGGER.info("Received path: " + page);
        LOGGER.info("Redirect to " + request.getContextPath() + page.replace(PAGE_REDIRECT, "/controller"));
        response.sendRedirect(request.getContextPath() + page.replace(PAGE_REDIRECT, "/controller"));

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
}


