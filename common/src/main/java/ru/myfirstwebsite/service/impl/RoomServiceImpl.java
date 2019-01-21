package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.domain.to.Room;
import ru.myfirstwebsite.service.RoomService;
import ru.myfirstwebsite.service.ServiceException;

import java.util.List;

public class RoomServiceImpl implements RoomService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();

    public RoomServiceImpl() {
    }

    @Override
    public Room loadById(int roomId) throws ServiceException {
        return null;
    }

    @Override
    public List<Room> findByApplication(Application applicationId) throws ServiceException {
        return null;
    }

    @Override
    public Integer create(Room entity) throws com.google.protobuf.ServiceException {
        return null;
    }

    @Override
    public List<Room> loadAll() throws com.google.protobuf.ServiceException {
        return null;
    }
}
