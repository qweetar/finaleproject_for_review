package ru.myfirstwebsite.controller.command;

import ru.myfirstwebsite.controller.command.impl.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class CommandHelper {
    private static final String ATTRIBUTE_COMMAND = "command";
    private static final String DASH = "-";
    private static final String UNDERSCORE = "_";

    private final Map<CommandName, CommandInterface> commands = new HashMap<>();

    public CommandHelper() {
        commands.put(CommandName.AUTHORIZATION, LoginCommand.getInstance());
        commands.put(CommandName.REGISTRATION, RegistrationCommand.getInstance());
        commands.put(CommandName.TEST_COMMAND, TestCommand.getInstance());
        //commands.put(CommandName.VIEW_USER, ViewUsersCommand.getInstance());
        commands.put(CommandName.APPLICATION, ApplicationCommand.getInstance());
        commands.put(CommandName.VIEW_ROOM, ViewRoomCommand.getInstance());
        commands.put(CommandName.VIEW_APPLICATION, ViewApplicationCommand.getInstance());
        commands.put(CommandName.UPDATE_APPLICATION, UpdateApplicationCommand.getInstance());
        commands.put(CommandName.DELETE_APPLICATION, DeleteApplicationCommand.getInstance());
        commands.put(CommandName.INDEX, LogoutCommand.getInstance());
    }

    /**
     * Method determines by request of which command is needed and returns the command object
     *
     * @param request HttpServletRequest
     * @return command object if command exists in map, else return null
     */
    public CommandInterface getCommand(HttpServletRequest request) {
        String commandName = request.getParameter(ATTRIBUTE_COMMAND);
        if(commandName != null) {
            CommandName name = CommandName.valueOf(commandName.toUpperCase().replace(DASH, UNDERSCORE));
            return commands.get(name);
        } else {
            return null;
        }
    }

    private enum CommandName {
        AUTHORIZATION, REGISTRATION, TEST_COMMAND, VIEW_APPLICATION, APPLICATION, VIEW_ROOM,
        USER, UPDATE_APPLICATION, DELETE_APPLICATION, INDEX
    }
}
