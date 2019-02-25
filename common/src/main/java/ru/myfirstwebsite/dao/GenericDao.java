package ru.myfirstwebsite.dao;

import ru.myfirstwebsite.exceptions.DaoException;
import java.util.List;

/**
 * Basic Data Access Object interface with template parameters.
 * @param <T> generic type of objects passed to methods
 * @param <K> generic type of objects which will serve as a key
 * Provides CRUD operations with {@link T} objects.
 * */
public interface GenericDao<T, K> {
    /**
     * Method find all nodes from database
     *
     * @return List {@link T} all nodes in database
     * @throws DaoException
     */
	List<T> findAll() throws DaoException;

    /**
     * Method find node from database by identification number
     *
     * @param id unique number of node in database
     * @return {@link T} - found record
     * @throws DaoException
     */
	T findById(K id) throws DaoException;

    /**
     * Method remove record in database by identification number
     *
     * @param id unique number of node in database
     * @return boolean value which defines state of transaction
     * @throws DaoException
     */
	boolean delete(K id) throws DaoException;

    /**
     * Method that create records in database
     *
     * @param entity object that necessary save in database
     * @return unique number created record
     * @throws DaoException
     */
	Integer create(T entity) throws DaoException;

    /**
     * Method update one of record in database with actually information
     *
     * @param entity object with new actually information
     * @return unique number updated record
     * @throws DaoException
     */
	Integer update(T entity) throws DaoException;
}
