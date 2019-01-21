package ru.myfirstwebsite.dao.factory;

import ru.myfirstwebsite.dao.ApplicationDao;
import ru.myfirstwebsite.dao.BillDao;
import ru.myfirstwebsite.dao.ReservationDao;
import ru.myfirstwebsite.dao.RoomDao;
import ru.myfirstwebsite.dao.UserDao;
import ru.myfirstwebsite.dao.impl.SQLApplicationDao;
import ru.myfirstwebsite.dao.impl.SQLBillDao;
import ru.myfirstwebsite.dao.impl.SQLReservationDao;
import ru.myfirstwebsite.dao.impl.SQLRoomDao;
import ru.myfirstwebsite.dao.impl.SQLUserDao;

public class SQLDaoFactory extends DaoFactory {
    private static final SQLDaoFactory instance = new SQLDaoFactory();

    private SQLDaoFactory(){}

    public static SQLDaoFactory getInstance(){
        return instance;
    }

    @Override
    public UserDao getUserDao() {
        return SQLUserDao.getInstance();
    }

    @Override
    public RoomDao getRoomDao() {
        return SQLRoomDao.getInstance();
    }

//    @Override
//    public ClientDao getClientDao() {
//        return SQLClientDao.getInstance();
//    }
//
    @Override
    public ReservationDao getReservationDao() {
        return SQLReservationDao.getInstance();
    }

    @Override
    public ApplicationDao getApplicationDao() {
        return SQLApplicationDao.getInstance();
    }

    @Override
    public BillDao getBillDao() {
        return SQLBillDao.getInstance();
    }
}
