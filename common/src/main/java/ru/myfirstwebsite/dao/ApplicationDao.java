package ru.myfirstwebsite.dao;

import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.exceptions.DaoException;

import java.util.List;

public interface ApplicationDao extends GenericDao <Application, Integer> {


    List<Application> getApplication (Integer userId) throws DaoException;
}
