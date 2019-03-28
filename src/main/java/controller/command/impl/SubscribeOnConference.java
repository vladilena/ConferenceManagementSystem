package controller.command.impl;

import controller.command.Command;
import model.entity.User;
import model.service.UserService;
import model.service.impl.DefaultUserService;
import model.util.AttributesManager;
import model.util.PathManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SubscribeOnConference implements Command {
    private static UserService userService;

    public SubscribeOnConference() {
        userService = DefaultUserService.getInstance();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        User user = (User)request.getSession().getAttribute("user");
        long conferenceId = Long.valueOf(request.getParameter("conference_id"));
        if(userService.subscribeOnConference(user.getId(), conferenceId)){
            //success
            request.setAttribute(AttributesManager.getProperty("success.subscribe"), true);

        }else {
            //false
            request.setAttribute(AttributesManager.getProperty("already.subscribed"), true);
        }
        return PathManager.getProperty("path.page.conference");
    }
}


