package ru.myfirstwebsite.controller.command.impl;


import ru.myfirstwebsite.controller.command.CommandException;
import ru.myfirstwebsite.controller.command.CommandInterface;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.service.UserService;
import ru.myfirstwebsite.service.impl.UserServiceImpl;
import ru.myfirstwebsite.service.validator.ValidationException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/** Class is designed for the registration of a new client
        */
public class RegistrationCommand implements CommandInterface {
    private static final UserService SERVICE = UserServiceImpl.getInstance();

    private static final String USER_ROLE = "user";
    private static final String CLIENT_ROLE = "client";

    private static final String NAME_ATTRIBUTE = "name";
    private static final String SURNAME_ATTRIBUTE = "surname";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ACTION = "action";
    private static final String REDIRECT_ACTION_ATTRIBUTE = "redirect";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";

    private static final String ERROR_FLAG = "errorFlag";
    private static final int ERROR_FLAG_VALUE = 1;
    private static final int ERROR_FLAG_VALUE_2 = 2;

    private RegistrationCommand(){}

    public static CommandInterface getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new RegistrationCommand();
    }




            /** Method performs the procedure for adding a new customer to the system.
            * Getting all information about new client and then create new node in system.
   * Also determines what action must be made for transition(forward or sendRedirect).
            *
            * @param request HttpServletRequest
     * @param response HttpServletResponse
     * @return the path to go to a specific page
     * @throws CommandException when creating fail
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {

        PagePath page = null;
        try {
            String name = request.getParameter(NAME_ATTRIBUTE);
            String surname = request.getParameter(SURNAME_ATTRIBUTE);
            String phone = request.getParameter(PHONE);
            String email = request.getParameter(EMAIL);
            String login = request.getParameter(LOGIN);
            String password = request.getParameter(PASSWORD);

            User client = new User();
            client.setUserName(name);
            client.setSurname(surname);
            client.setMobilePhone(phone);
            client.setEmail(email);
            client.setLogin(login);
            client.setPassword(password);

            User resultClient = SERVICE.create(client);
            if(resultClient == null) {
                request.setAttribute(ACTION, REDIRECT_ACTION_ATTRIBUTE);
                page = PagePath.ERROR;
            } else {
                HttpSession session = request.getSession(true);
                page = PagePath.INDEX;
                session.setAttribute(CLIENT_ROLE, resultClient);
                request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
            }
        } catch (Exception e) {
            throw new CommandException("Command Exception", e);
        }
        return page.toString().toLowerCase();
    }
}



