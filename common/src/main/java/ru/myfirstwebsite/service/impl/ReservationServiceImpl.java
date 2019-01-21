package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Reservation;
import ru.myfirstwebsite.service.ReservationService;
import ru.myfirstwebsite.service.ServiceException;

import java.util.List;

public class ReservationServiceImpl implements ReservationService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();

    @Override
    public Reservation loadById(int reservationId) throws ServiceException {
        return null;
    }

    @Override
    public List<Reservation> findByReservation(Reservation reservationId) throws ServiceException {
        return null;
    }

    @Override
    public Integer create(Reservation entity) throws com.google.protobuf.ServiceException {
        return null;
    }

    @Override
    public List<Reservation> loadAll() throws com.google.protobuf.ServiceException {
        return null;
    }
}
