package ru.myfirstwebsite.dao.impl;

import ru.myfirstwebsite.dao.UserDao;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPool;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPoolException;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLUserDao implements UserDao {

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String ROLE = "role";
    private static final String CLIENT_ID = "iduser";
    private static final String USER_NAME = "name";
    private static final String USER_SURNAME = "surname";
    private static final String MOBILE_PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String BLOCKED = "blocked";
    //private static final Sex SEX = Sex.valueOf("sex");

    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    // Request from database, should do something
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE iduser = ?";
    private static final String USER_BY_LOG_PASS = "SELECT * FROM user WHERE login = ? AND password = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user";


    //Demand Holder Idiom - Singletone
    private SQLUserDao(){}

    public static UserDao getInstance() {
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final UserDao instance = new SQLUserDao();
    }


    @Override
    public User getUserNode(String login, String password) throws DaoException, NoSuchEntityException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(USER_BY_LOG_PASS)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                User user = new User();
                user.setUserId((long) set.getInt(CLIENT_ID));
                user.setUserName(set.getString(USER_NAME));
                user.setSurname(set.getString(USER_SURNAME));
                user.setMobilePhone(set.getString(MOBILE_PHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getBoolean(String.valueOf(BLOCKED)));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                user.setRole(set.getString(ROLE));
                //user.setSex(Sex.valueOf(set.getString(String.valueOf(SEX))));
                return user;
            } else {
                throw new NoSuchEntityException("There is no such user");
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }


    @Override
    public boolean checkUser(String login, String password) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(USER_BY_LOG_PASS)) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet set = statement.executeQuery();

                return set.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
    @Override
    public List<User> findAll() throws DaoException {
        List<User> userList = new ArrayList<>();
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_USERS)) {
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                User user = new User();
                user.setUserId((long) set.getInt(CLIENT_ID));
                user.setUserName(set.getString(USER_NAME));
                user.setSurname(set.getString(USER_SURNAME));
                user.setMobilePhone(set.getString(MOBILE_PHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getBoolean(String.valueOf(BLOCKED)));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                user.setRole(set.getString(ROLE));
                userList.add(user);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return userList;
    }

    public User findById(Long id) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setLong(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                User user = new User();
                user.setUserId((long) set.getInt(CLIENT_ID));
                user.setUserName(set.getString(USER_NAME));
                user.setSurname(set.getString(USER_SURNAME));
                user.setMobilePhone(set.getString(MOBILE_PHONE));
                user.setEmail(set.getString(EMAIL));
                user.setBlocked(set.getBoolean(String.valueOf(BLOCKED)));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
                user.setRole(set.getString(ROLE));
                return user;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    public boolean delete(Long id) throws DaoException {
        return false;
    }

    public int create(User entity) throws DaoException {
        return 0;
    }

    public Long update(User entity) throws DaoException {
        return null;
    }
}
