package ru.myfirstwebsite.service;

import ru.myfirstwebsite.domain.to.Application;

import java.util.List;

public interface ApplicationService extends GenericServiceInterface <Application, Integer> {

    List<Application> loadApplication(int applicationId) throws ServiceException;

    Application findApplicationById(int applicationId) throws ServiceException;
}
