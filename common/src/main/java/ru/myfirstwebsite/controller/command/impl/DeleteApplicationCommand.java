package ru.myfirstwebsite.controller.command.impl;

import ru.myfirstwebsite.controller.command.CommandException;
import ru.myfirstwebsite.controller.command.CommandInterface;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.service.ApplicationService;
import ru.myfirstwebsite.service.ServiceException;
import ru.myfirstwebsite.service.impl.ApplicationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.Date;

public class DeleteApplicationCommand implements CommandInterface {
    private static final String ACTION = "action";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";


    private static final ApplicationService SERVICE = ApplicationServiceImpl.getInstance();

    private static final String APPLICATION_ID = "idapplication";

    private DeleteApplicationCommand() {

    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new DeleteApplicationCommand();
    }

    /**
     * Method performs the procedure for adding hotel room information on page and further viewing and updating
     * Also determines what action must be made for transition(forward or sendRedirect)
     *
     * @param request  HttpServletRequest
     * @param response HttpServletResponse
     * @return the path to go to a specific page
     * @throws CommandException when getting all nodes fail
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PagePath page = null;
        try{

            String idapplication = request.getParameter(APPLICATION_ID);
            boolean check = SERVICE.deleteApplication(Integer.valueOf(idapplication));
            if(check) {
                page = PagePath.APPLICATION_DELETE;

            } else  {
                page = PagePath.ERROR;
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        return page.toString().toLowerCase();
    }

}
