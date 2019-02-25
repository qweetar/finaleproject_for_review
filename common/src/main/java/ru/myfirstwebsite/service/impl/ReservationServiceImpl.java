package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.ReservationDao;
import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Application;
import ru.myfirstwebsite.domain.to.Reservation;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.service.ReservationService;
import ru.myfirstwebsite.service.ServiceException;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();

    private ReservationServiceImpl(){}

    public static ReservationService getInstance(){
        return SingletonHolder.INSTANCE;
    }

    private static class SingletonHolder {
        private static final ReservationService INSTANCE = new ReservationServiceImpl();
    }

    @Override
    public Reservation loadById(int reservationId) throws ServiceException {
        try {
            ReservationDao reservationDao = FACTORY.getReservationDao();
            return reservationDao.findById(reservationId);

        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public List<Reservation> findByReservation(Reservation reservationId) throws ServiceException {
        return null;
    }

    @Override
    public Reservation create(Reservation reservation) throws ServiceException {
        try {
            ReservationDao reservationDao = FACTORY.getReservationDao();
            int reservationId = (int) reservationDao.create(reservation);

            if(reservationId == 0) {
                return null;
            } else {
                reservation.setReservationId(reservationId);
                return reservation;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public List<Reservation> loadAll() throws ServiceException {
        List<Reservation> list;
        try {
            ReservationDao reservationDao = FACTORY.getReservationDao();
            list = reservationDao.findAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }
}
