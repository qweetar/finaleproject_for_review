package ru.myfirstwebsite.dao.factory;

import ru.myfirstwebsite.dao.ApplicationDao;
import ru.myfirstwebsite.dao.BillDao;
import ru.myfirstwebsite.dao.ReservationDao;
import ru.myfirstwebsite.dao.RoomDao;
import ru.myfirstwebsite.dao.UserDao;

public abstract class DaoFactory {
    public static DaoFactory getDaoFactory() {
        return SQLDaoFactory.getInstance();
    }

    public abstract UserDao getUserDao();
    public abstract RoomDao getRoomDao();
//    public abstract ClientDao getClientDao();
    public abstract ReservationDao getReservationDao();
    public abstract ApplicationDao getApplicationDao();
    public abstract BillDao getBillDao();
}
