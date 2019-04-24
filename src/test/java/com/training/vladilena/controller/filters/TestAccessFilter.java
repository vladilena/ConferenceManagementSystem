package com.training.vladilena.controller.filters;

import com.training.vladilena.model.dao.data.RoleBuilder;
import com.training.vladilena.model.dao.data.UserBuilder;
import com.training.vladilena.model.entity.Role;
import com.training.vladilena.model.entity.User;
import com.training.vladilena.model.exceptions.PermissionErrorException;
import org.junit.jupiter.api.Test;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

 class TestAccessFilter {

    @Test
    void PermissionErrorWhenUserTryToCreateConference() throws IOException, ServletException {
        final AccessFilter filter = new AccessFilter();
        final FilterChain filterChain = mock(FilterChain.class);

        User user = UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        String uri = "/WEB-INF/view/moderator/create_conference.jsp";
        String command = "create_conference";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getRequestURI()).thenReturn(uri);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);
        when(request.getParameter("action")).thenReturn(command);

        assertThrows(PermissionErrorException.class, () -> filter.doFilter(request, response, filterChain));
    }

    @Test
     void PermissionErrorWhenUserTrySeeRestrictedPage() throws IOException, ServletException {
        final AccessFilter filter = new AccessFilter();
        final FilterChain filterChain = mock(FilterChain.class);

        User user = UserBuilder.getBuilder().constructUser(1L,
                RoleBuilder.getBuilder().constructRole(Role.USER)).build();
        String uri = "/WEB-INF/view/moderator/redirect_create_report.jsp";

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);

        when(request.getRequestURI()).thenReturn(uri);
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("user")).thenReturn(user);

        assertThrows(PermissionErrorException.class, ()->filter.doFilter(request, response, filterChain));

    }
}

