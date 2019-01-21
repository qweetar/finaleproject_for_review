package ru.myfirstwebsite.dao;

import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;

/* Interface for User table in database with concrete parameters.
        * Provides specific method with {@link User} objects
        */

public interface UserDao extends GenericDao<User, Long> {
  /**
   * Method get {@link User} object from database by login and password
   *
   * @param login login unique parameter
   * @param password password parameter
   * @return {@link User} object
   * @throws DaoException
   */
    User getUserNode(String login, String password) throws DaoException, NoSuchEntityException;

    /**
     * Method check user node in database by login and password transfers parameters
     *
     * @param login login unique parameter
     * @param password password parameter
     * @return boolean result of operation
     * @throws DaoException
     */
    boolean checkUser(String login, String password) throws DaoException;
}
