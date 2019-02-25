package ru.myfirstwebsite.controller.command.impl;


import ru.myfirstwebsite.controller.command.CommandException;
import ru.myfirstwebsite.controller.command.CommandInterface;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;
import ru.myfirstwebsite.service.ServiceException;
import ru.myfirstwebsite.service.UserService;
import ru.myfirstwebsite.service.impl.UserServiceImpl;
import ru.myfirstwebsite.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Class is designed for the log in in system as administrator or client
        */
public class LoginCommand implements CommandInterface {

    private static final UserService SERVICE = UserServiceImpl.getInstance();
    //private static final PagesConfigManager MANAGER = PagesConfigManager.getInstance();
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ADMIN_ROLE = "admin";
    private static final String CLIENT_ROLE = "client";
    private static final String USER = "user";
    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private LoginCommand(){}

    public static CommandInterface getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new LoginCommand();
    }

    /** Method performs the procedure for authorization in system
     * In first, getting login and password parameters from request
     * Then finding node with equals parameters. If procedure return not null, then necessary define client or admin
     * log in. According to role of user creating admin or client object and put into session.
            *
            * Also determines what action must be made for transition(forward or sendRedirect)
     *
             * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return the path to go to a specific page
     * @throws CommandException if authorization method process fail
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        PagePath page = null;

            User tempUser = new User();
            tempUser.setLogin(request.getParameter(LOGIN));
            tempUser.setPassword(request.getParameter(PASSWORD));

            HttpSession session = request.getSession(true);
            User user = null;

            try {
            user = SERVICE.authorization(tempUser);
            } catch (ServiceException e) {
                e.printStackTrace();
            } catch (NoSuchEntityException e) {
                e.printStackTrace();
            }

        if(user == null) {
               // request.setAttribute(ERROR_FLAG, ERROR_FLAG_VALUE); // One of the realization to show error message for client
                request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE); // If there is no user with this id, show the message
                page = PagePath.REGISTRATION;
            } else {
                page = PagePath.RESULT;
                session.setAttribute(USER, user);
                request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            }

        return page.toString().toLowerCase();
    }
}
