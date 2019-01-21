package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.service.ApplicationService;
import ru.myfirstwebsite.service.ServiceException;

import java.util.List;

public class ApplicationServiceImpl implements ApplicationService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();

    @Override
    public List<Application> loadApplication(int applicationId) throws ServiceException {
        return null;
    }

    @Override
    public Application findApplicationById(int applicationId) throws ServiceException {
        return null;
    }

    @Override
    public Integer create(Application entity) throws com.google.protobuf.ServiceException {
        return null;
    }

    @Override
    public List<Application> loadAll() throws com.google.protobuf.ServiceException {
        return null;
    }
}
