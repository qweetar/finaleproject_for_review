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
    private static final String LAST_ID = "lastId";

    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    // Request from database, should do something
    private static final String SELECT_BY_ID = "SELECT * FROM user WHERE iduser = ?";
    private static final String USER_BY_LOG_PASS = "SELECT * FROM user WHERE login = ? AND password = ?";
    private static final String SELECT_ALL_USERS = "SELECT * FROM user";
    private static final String DELETE_USER = "DELETE FROM user WHERE iduser = ? & role = client";
    private static final String CREATE_USER = "INSERT INTO user (login, password, name, surname, phone, email, status)" +
            " VALUES ( ?, ?, ?, ?, ?, ?, ?)";
    //private static final String CREATE_ROLE = "INSERT INTO role (idrole, rolename, iduser) VALUES (?, ?, ?)";
    private static final String UPDATE_USER = "UPDATE user SET user.email = ?, user.phone = ? " +
            "WHERE user.iduser = ?";
    private static final String LAST_INSERT_ID = "SELECT last_insert_id() as lastId";





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
                user.setUserId(set.getInt(CLIENT_ID));
                user.setLogin(set.getString(LOGIN));
                user.setPassword(set.getString(PASSWORD));
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
                user.setUserId(set.getInt(CLIENT_ID));
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
                user.setUserId( set.getInt(CLIENT_ID));
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
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(DELETE_USER)) {
            statement.setLong(1, id);
            statement.executeUpdate();
            return true;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Integer create(User user) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(CREATE_USER);
//            PreparedStatement statementNew = connect.prepareStatement(CREATE_ROLE);
            PreparedStatement statementThird = connect.prepareStatement(LAST_INSERT_ID)) {
            connect.setAutoCommit(false);

            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getUserName());
            statement.setString(4, user.getSurname());
            statement.setString(5, user.getMobilePhone());
            statement.setString(6, user.getEmail());
            statement.setString(7, String.valueOf(user.isBlocked()));
            statement.executeUpdate();
            connect.commit();

            ResultSet set = statementThird.executeQuery();
            set.next();
            return set.getInt(LAST_ID);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    public Integer update(User user) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(UPDATE_USER)) {
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getMobilePhone());
            statement.setLong(3, user.getUserId());
            statement.executeUpdate();

            return user.getUserId();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }
}
