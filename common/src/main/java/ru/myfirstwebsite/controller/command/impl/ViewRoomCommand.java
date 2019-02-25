package ru.myfirstwebsite.controller.command.impl;

import ru.myfirstwebsite.controller.command.CommandException;
import ru.myfirstwebsite.controller.command.CommandInterface;
import ru.myfirstwebsite.service.RoomService;
import ru.myfirstwebsite.service.ServiceException;
import ru.myfirstwebsite.service.UserService;
import ru.myfirstwebsite.service.impl.RoomServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ViewRoomCommand implements CommandInterface {
    private static final String ACTION = "action";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    private static final RoomService SERVICE = RoomServiceImpl.getInstance();

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new ViewRoomCommand();
    }

    private ViewRoomCommand() {

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PagePath page = null;

        try {
            page = PagePath.VIEW_ROOM;
            request.setAttribute("roomList", SERVICE.loadAll());
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
        return page.toString().toLowerCase();
    }
}
