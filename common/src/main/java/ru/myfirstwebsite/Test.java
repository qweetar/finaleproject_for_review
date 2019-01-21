package ru.myfirstwebsite;

import ru.myfirstwebsite.dao.ApplicationDao;
import ru.myfirstwebsite.dao.RoomDao;
import ru.myfirstwebsite.dao.UserDao;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPool;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPoolException;
import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Room;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;

public class Test {
    private static DaoFactory factory = DaoFactory.getDaoFactory();

    public static void main(String[] args) throws ConnectionPoolException, NoSuchEntityException {
        ConnectionPool.getInstance().init();
        UserDao userDao = factory.getUserDao();
        RoomDao roomDao = factory.getRoomDao();
        ApplicationDao applicationDao = factory.getApplicationDao();
        try {
            System.out.println(userDao.findById(1L));
            System.out.println("1 - " + userDao.checkUser("fdurst", "12345"));
            System.out.println("2 - " + userDao.getUserNode("fdurst", "12345"));
            System.out.println(userDao.findAll());
            System.out.println("__________________");
            System.out.println("1 - " + roomDao.findById(2));
            System.out.println("2 - " + applicationDao.findAll());

            System.out.println(roomDao.create(new Room())); // Ask about this situation???
        } catch (DaoException e) {
            e.printStackTrace();
        }
    }
}
