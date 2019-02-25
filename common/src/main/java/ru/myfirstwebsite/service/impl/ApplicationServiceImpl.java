package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.ApplicationDao;
import ru.myfirstwebsite.dao.RoomDao;
import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.service.ApplicationService;
import ru.myfirstwebsite.service.ServiceException;
import ru.myfirstwebsite.service.validator.ApplicationValidator;
import ru.myfirstwebsite.service.validator.ValidationException;
import ru.myfirstwebsite.service.validator.ValidatorInterface;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<Application> VALIDATE = ApplicationValidator.getInstance();

    private ApplicationServiceImpl(){}

    public static ApplicationService getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ApplicationService instance = new ApplicationServiceImpl();
    }

    @Override
    public List<Application> loadApplication(int id) throws ServiceException {
        List<Application> list;
        try {
            ApplicationDao applicationDao = FACTORY.getApplicationDao();
            list = applicationDao.getUserApplication(id);
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public Application findApplicationById(int id) throws ServiceException {
        try {
            ApplicationDao applicationDao = FACTORY.getApplicationDao();
            Application application = applicationDao.findById(id);
            if(application != null) {
                return application;
            } else {
                return null;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public Application create(Application application) throws ServiceException {
        ApplicationDao applicationDao = FACTORY.getApplicationDao();
        try {
            application.setApplicationId(applicationDao.create(application));
        } catch (DaoException e) {
            e.printStackTrace();
            throw new UnsupportedOperationException();
        }
        return application;
    }


    @Override
    public List<Application> loadAll() throws ServiceException {
        List<Application> list;
        try {
            ApplicationDao applicationDao = FACTORY.getApplicationDao();
            list = applicationDao.findAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public boolean updateApplication(Application application) throws ServiceException {
        try {
            if(VALIDATE.isValid(application)) {
                ApplicationDao applicationDao = FACTORY.getApplicationDao();
                applicationDao.update(application);
                return true;
            } else {
                throw new ValidationException("ValidationException");
            }
        } catch (DaoException | ValidationException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public boolean deleteApplication(int id) throws ServiceException {
        try {
            ApplicationDao applicationDao = FACTORY.getApplicationDao();
            return applicationDao.delete(id);
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }
}
