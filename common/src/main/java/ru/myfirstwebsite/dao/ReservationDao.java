package ru.myfirstwebsite.dao;

import ru.myfirstwebsite.domain.to.Reservation;
import ru.myfirstwebsite.exceptions.DaoException;

public interface ReservationDao extends GenericDao<Reservation, Integer> {
    boolean checkReservation(Integer roomId) throws DaoException;
}
