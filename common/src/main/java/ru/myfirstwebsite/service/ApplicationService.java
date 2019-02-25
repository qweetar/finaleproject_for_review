package ru.myfirstwebsite.service;

import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.service.ApplicationService;

import java.util.List;

public interface ApplicationService extends GenericServiceInterface <Application, Integer> {

    List<Application> loadApplication(int applicationId) throws ServiceException;

    Application findApplicationById(int applicationId) throws ServiceException;

    boolean updateApplication(Application application) throws ServiceException;

    boolean deleteApplication(int id) throws ServiceException;
}
