package ru.myfirstwebsite.service.impl;

import com.google.protobuf.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;
import ru.myfirstwebsite.dao.UserDao;
import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.User;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;
import ru.myfirstwebsite.service.UserService;
import ru.myfirstwebsite.service.validator.LoginValidator;
import ru.myfirstwebsite.service.validator.ValidationException;
import ru.myfirstwebsite.service.validator.ValidatorInterface;

import java.util.List;

public class UserServiceImpl implements UserService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();
    private static final ValidatorInterface<User> VALIDATE = LoginValidator.getInstance();

    private UserServiceImpl(){}

    /**
     * Method check login and password information from some user and get user object if authorization success
     * @param user object, that provides authorization operation
     * @return null if client not exists in system; user object if authorization execute correctly
     * @throws ServiceException
     */
    @Override
    public User authorization(User user) throws ServiceException, NoSuchEntityException {
        try {
            UserDao userDao = FACTORY.getUserDao();

            if(VALIDATE.isValid(user)) {

                String password = user.getPassword();
                String passwordMD5 = DigestUtils.md5Hex(password);
                user.setPassword(passwordMD5);

                boolean check = userDao.checkUser(user.getLogin(), user.getPassword());// this method for made
                if (!check) {
                    return null;
                } else {
                    return userDao.getUserNode(user.getLogin(), user.getPassword()); // this method for made
                }
            } else {
                throw new ValidationException("Validation Exception");
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    public static UserService getInstance() {
        return SingletonHolder.instance;
    }

    @Override
    public User loadById(Long userId) throws ServiceException, NoSuchEntityException {
        return null;
    }


    private static class SingletonHolder {
        private static final UserServiceImpl instance = new UserServiceImpl();
    }

    @Override
    public Long create(User obj) throws ServiceException {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<User> loadAll() throws ServiceException {
        return null;
    }

}
