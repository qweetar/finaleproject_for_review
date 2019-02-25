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

public class UpdateApplicationCommand implements CommandInterface {
    private static final String ACTION = "action";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";


    private static final ApplicationService SERVICE = ApplicationServiceImpl.getInstance();

    private static final String APPLICATION_ID = "idapplication";
    private static final String ROOM_SIZE = "roomsize";
    private static final String ROOM_ClASS = "roomclass";
    private static final String DATE_FROM = "datefrom";
    private static final String DATE_TO = "dateto";
    private static final String ID_USER = "user";

    private UpdateApplicationCommand() {

    }

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new UpdateApplicationCommand();
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
            HttpSession session = request.getSession();
            User tempUser = (User)session.getAttribute(ID_USER);

            String idapplication = request.getParameter(APPLICATION_ID);
            String roomsize = request.getParameter(ROOM_SIZE);
            String roomclass = request.getParameter(ROOM_ClASS);
            String datefrom = request.getParameter(DATE_FROM);
            String dateto = request.getParameter(DATE_TO);

            Application application = new Application();
            application.setApplicationId(Integer.valueOf(idapplication));
            application.setRoomSize(Integer.valueOf(roomsize));
            application.setRoomClass(roomclass);
            application.setDateFrom(Date.valueOf(datefrom));
            application.setDateTo(Date.valueOf(dateto));
            application.setUserId(tempUser.getUserId());

            boolean check = SERVICE.updateApplication(application);
            if(check) {
                page = PagePath.APPLICATION;
                //request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

            } else  {
                //request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
                page = PagePath.ERROR;
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);

        return page.toString().toLowerCase();
    }

}
