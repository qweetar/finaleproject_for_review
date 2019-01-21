package ru.myfirstwebsite.dao;

import ru.myfirstwebsite.domain.to.Room;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;

/**Interface for User table in database with concrete parameters.
        * Provides specific method with {@link Room} objects
        */

public interface RoomDao extends GenericDao<Room, Integer> {
  /**
   * Method get {@link Room} object from database by room#
   *
   * @param roomNum number unique parameter
   * @return boolean
   * @throws DaoException
   */
    Room roomNode (Integer roomNum) throws DaoException, NoSuchEntityException;

    /**
     * Method check user node in database by login and password transfers parameters
     *
     * @param  roomNum number unique parameter
     * @return boolean result of operation
     * @throws DaoException
     */
    boolean checkRoom(Integer roomNum) throws DaoException;
}
