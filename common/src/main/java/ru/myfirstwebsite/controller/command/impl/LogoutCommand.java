package ru.myfirstwebsite.controller.command.impl;


import ru.myfirstwebsite.controller.command.CommandException;
import ru.myfirstwebsite.controller.command.CommandInterface;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;
import ru.myfirstwebsite.service.ServiceException;
import ru.myfirstwebsite.service.UserService;
import ru.myfirstwebsite.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Class is designed for the log in in system as administrator or client
        */
public class LogoutCommand implements CommandInterface {

    private static final String INDEX = "/index";
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private LogoutCommand(){}

    public static CommandInterface getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new LogoutCommand();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {


            HttpSession session = request.getSession();
            session.invalidate();
            request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

            return INDEX;
        }
}
