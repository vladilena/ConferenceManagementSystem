package controller.command.impl;

import controller.command.Command;
import model.exception.PageNotFoundException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EmptyCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        throw new PageNotFoundException("An empty command");
    }
}


