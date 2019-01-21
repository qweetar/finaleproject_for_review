package ru.myfirstwebsite.service;

import ru.myfirstwebsite.domain.to.Reservation;

import java.util.List;

public interface ReservationService extends GenericServiceInterface <Reservation, Integer> {
    Reservation loadById(int reservationId) throws ServiceException;

    List<Reservation> findByReservation(Reservation reservationId) throws  ServiceException;

}
