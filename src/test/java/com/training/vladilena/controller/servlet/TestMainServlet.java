package com.training.vladilena.controller.servlet;

import com.training.vladilena.model.service.LoginService;
import org.junit.jupiter.api.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class TestMainServlet {
    @Test
    public void UserNotExistsError() throws ServletException, IOException {
        final MainServlet servlet = new MainServlet();

        String login = "login";
        String password = "password";
        String commandUri = "login";
        String path = "/WEB-INF/view/login.jsp";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        final HttpSession session = mock(HttpSession.class);
        final LoginService service = mock(LoginService.class);

        when(request.getRequestURI()).thenReturn(commandUri);
        when(request.getRequestDispatcher(path)).thenReturn(dispatcher);
        when(request.getParameter("action")).thenReturn(commandUri);
        when(request.getParameter("login")).thenReturn(login);
        when(request.getParameter("password")).thenReturn(password);
        when(request.getSession()).thenReturn(session);
        when(service.login(login, password)).thenReturn(null);
        servlet.doPost(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, times(3)).getParameter(anyString());
        verify(request, times(1)).setAttribute(("loginError"), true);
        verify(dispatcher).forward(request, response);
    }
}


