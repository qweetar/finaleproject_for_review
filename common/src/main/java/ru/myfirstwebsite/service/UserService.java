package ru.myfirstwebsite.service;

import ru.myfirstwebsite.service.ServiceException;

import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;

public interface UserService extends GenericServiceInterface <User, Long> {
    User loadById(Long userId) throws ServiceException, NoSuchEntityException;

    /**
     * Method provides operation for login user
     *
     * @param user object, that provides authorization operation
     * @return {@link User} - login record
     * @throws ServiceException
     */
    User authorization(User user) throws ServiceException, NoSuchEntityException;
}
