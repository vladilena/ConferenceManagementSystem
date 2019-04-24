package com.training.vladilena.controller.command;

import com.training.vladilena.controller.command.impl.EmptyCommand;
import org.junit.jupiter.api.Test;

import javax.servlet.http.HttpServletRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TestCommandFactory {
    @Test
    void whenWrongCommandCameThenReturnEmptyCommand() {
        String action = "wrongCommand";
        final CommandFactory factory = CommandFactory.getInstance();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        Command emptyCommand = new EmptyCommand();

        when(request.getParameter("action")).thenReturn(action);
        Command resultCommand = factory.getCommand(request);
        assertEquals(resultCommand.getClass(), emptyCommand.getClass());
    }
}
