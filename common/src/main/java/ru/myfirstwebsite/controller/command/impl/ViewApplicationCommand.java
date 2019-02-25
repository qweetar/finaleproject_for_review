package ru.myfirstwebsite.controller.command.impl;

import ru.myfirstwebsite.controller.command.CommandException;
import ru.myfirstwebsite.controller.command.CommandInterface;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.service.ApplicationService;
import ru.myfirstwebsite.service.ServiceException;
import ru.myfirstwebsite.service.impl.ApplicationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewApplicationCommand implements CommandInterface {
    private static final String ACTION = "action";
    private static final String FORWARD_ACTION_ATTRIBUTE = "forward";
    private static final ApplicationService SERVICE = ApplicationServiceImpl.getInstance();

    private static final String ID_USER = "user";

    public static CommandInterface getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final CommandInterface INSTANCE = new ViewApplicationCommand();
    }

    private ViewApplicationCommand() {

    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws CommandException {
        PagePath page = null;

        try {
            HttpSession session = request.getSession();
            User tempUser = (User)session.getAttribute(ID_USER);
            page = PagePath.VIEW_APPLICATION;
            request.setAttribute("applicationList", SERVICE.loadApplication(tempUser.getUserId()));
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        request.setAttribute(ACTION, FORWARD_ACTION_ATTRIBUTE);
        return page.toString().toLowerCase();
    }
}
