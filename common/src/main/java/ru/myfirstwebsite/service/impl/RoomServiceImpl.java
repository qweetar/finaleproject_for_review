package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.RoomDao;
import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.domain.to.Room;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.service.RoomService;
import ru.myfirstwebsite.service.ServiceException;
import ru.myfirstwebsite.service.validator.RoomValidator;
import ru.myfirstwebsite.service.validator.ValidationException;
import ru.myfirstwebsite.service.validator.ValidatorInterface;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();

    private static final ValidatorInterface<Room> VALIDATE = RoomValidator.getInstance();

    public RoomServiceImpl() {
    }

    public static RoomService getInstance(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final RoomService instance = new RoomServiceImpl();
    }



    @Override
    public Room loadById(int roomId) throws ServiceException {
        Room room;
        try {
            RoomDao roomDao = FACTORY.getRoomDao();
            room = roomDao.findById(roomId);
            if(room != null) {
                return room;
            } else {
                return null;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public List<Room> findByApplication(Application applicationId) throws ServiceException {
        return null;
    }

    @Override
    public Room create(Room room) throws ServiceException {
        try {
            if(VALIDATE.isValid(room)) {
                RoomDao roomDao = FACTORY.getRoomDao();
                roomDao.create(room);
                return room;
            } else {
                throw new ValidationException("Validation Exception");
            }
        } catch (DaoException | ValidationException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public List<Room> loadAll() throws ServiceException {
        List<Room> list;
        try {
            RoomDao roomDao = FACTORY.getRoomDao();
            list = roomDao.findAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }
}
