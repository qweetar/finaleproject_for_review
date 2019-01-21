package ru.myfirstwebsite.service;

import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.domain.to.Room;

import java.util.List;

public interface RoomService extends GenericServiceInterface <Room, Integer> {

    Room loadById(int roomId) throws ServiceException;

    List<Room> findByApplication(Application applicationId) throws  ServiceException;
}
