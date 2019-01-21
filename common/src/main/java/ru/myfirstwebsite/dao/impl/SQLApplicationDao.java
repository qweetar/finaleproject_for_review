package ru.myfirstwebsite.dao.impl;

import ru.myfirstwebsite.dao.ApplicationDao;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPool;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPoolException;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLApplicationDao implements ApplicationDao {

    private static final String APPLICATION_ID = "idapplication";
    private static final String ROOM_CLASS = "class";
    private static final String ROOM_SIZE = "roomsize";
    private static final String  DATE_FROM = "datefrom";
    private static final String  DATE_TO = "dateto";
    private static final String  USER_ID = "iduser";

    private static final String SELECT_APPLICATION_USER = "SELECT * FROM application WHERE iduser = ?";
    private static final String SELECT_ALL_APPLICATIONS = "SELECT * FROM application";
    private static final String SELECT_BY_ID = "SELECT * FROM application WHERE idapplication = ?";
    private static final String DELETE_APPLICATION = "DELETE FROM application WHERE idapplication = ?";



    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private SQLApplicationDao(){}

    public static ApplicationDao getInstance() {
        return SQLApplicationDao.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ApplicationDao instance = new SQLApplicationDao();
    }

    @Override
    public List<Application> getApplication(Integer userId) throws DaoException {
        List<Application> list = new ArrayList<>();
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_APPLICATION_USER)) {
            statement.setInt(1, userId);
            ResultSet set = statement.executeQuery();

            while(set.next()) {
                Application application = new Application();
                application.setApplicationId(set.getInt(APPLICATION_ID));
                application.setRoomClass(set.getString(ROOM_CLASS));
                application.setRoomSize(set.getInt(ROOM_SIZE));
                application.setDateFrom(set.getDate(DATE_FROM));
                application.setDateTo(set.getDate(DATE_TO));
                application.setUserId(set.getInt(USER_ID));
                list.add(application);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("DAOException", e);
        }
        return list;
    }

    @Override
    public List<Application> findAll() throws DaoException {
        List<Application> list = new ArrayList<>();
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_APPLICATIONS)) {
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Application application = new Application();
                application.setApplicationId(set.getInt(APPLICATION_ID));
                application.setRoomClass(set.getString(ROOM_CLASS));
                application.setRoomSize(set.getInt(ROOM_SIZE));
                application.setDateFrom(set.getDate(DATE_FROM));
                application.setDateTo(set.getDate(DATE_TO));
                application.setUserId(set.getInt(USER_ID));
                list.add(application);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return list;
    }

    @Override
    public Application findById(Integer id) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                Application application = new Application();
                application.setApplicationId(set.getInt(APPLICATION_ID));
                application.setRoomClass(set.getString(ROOM_CLASS));
                application.setRoomSize(set.getInt(ROOM_SIZE));
                application.setDateFrom(set.getDate(DATE_FROM));
                application.setDateTo(set.getDate(DATE_TO));
                application.setUserId(set.getInt(USER_ID));
                return application;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_APPLICATION)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public int create(Application entity) throws DaoException {
        return 0;
    }

    @Override
    public Integer update(Application entity) throws DaoException {
        return null;
    }
}
