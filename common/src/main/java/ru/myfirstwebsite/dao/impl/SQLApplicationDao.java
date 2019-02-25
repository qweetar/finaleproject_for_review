package ru.myfirstwebsite.dao.impl;

import ru.myfirstwebsite.dao.ApplicationDao;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPool;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPoolException;
import ru.myfirstwebsite.domain.to.Application;
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
    private static final String LAST_ID = "lastId";


    private static final String SELECT_APPLICATION_USER = "SELECT * FROM application WHERE iduser = ?";
    private static final String SELECT_ALL_APPLICATIONS = "SELECT * FROM application";
    private static final String SELECT_BY_ID = "SELECT * FROM application WHERE idapplication = ?";
    private static final String DELETE_APPLICATION = "DELETE FROM application WHERE idapplication = ?";
    private static final String CREATE_APPLICATION = "INSERT INTO application(roomsize, class, datefrom, dateto, iduser)" +
            " VALUES ( ?, ?, ?, ?, ?)";
    private static final String LAST_INSERT_ID = "SELECT last_insert_id() as lastId";
    private static final String UPDATE_APPLICATION = "UPDATE application SET roomsize = ?," +
            "class = ?, datefrom = ?, dateto = ? " +
            "WHERE idapplication = ?";



    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private SQLApplicationDao(){}

    public static ApplicationDao getInstance() {
        return SQLApplicationDao.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ApplicationDao instance = new SQLApplicationDao();
    }

    @Override
    public List<Application> getUserApplication(Integer userId) throws DaoException {
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
    public Integer create(Application application) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(CREATE_APPLICATION);
            PreparedStatement statementThird = connect.prepareStatement(LAST_INSERT_ID)) {
            connect.setAutoCommit(false);

            //statement.setInt(1, application.getApplicationId());
            statement.setInt(1, application.getRoomSize());
            statement.setString(2, application.getRoomClass());
            statement.setDate(3, (java.sql.Date) application.getDateFrom());
            statement.setDate(4, (java.sql.Date) application.getDateTo());
            statement.setInt(5, application.getUserId());
//            statement.setInt(6, application.getRoomId());
            statement.executeUpdate();
            connect.commit();

            ResultSet set = statementThird.executeQuery();
            set.next();
            return set.getInt(LAST_ID);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Integer update(Application application) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(UPDATE_APPLICATION)) {

            statement.setInt(1, application.getRoomSize());
            statement.setString(2, application.getRoomClass());
            statement.setDate(3, (java.sql.Date) application.getDateFrom());
            statement.setDate(4, (java.sql.Date) application.getDateTo());
            statement.setInt(5, application.getApplicationId());
            statement.executeUpdate();

            return application.getApplicationId();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}
