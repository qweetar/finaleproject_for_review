package ru.myfirstwebsite.dao.impl;

import ru.myfirstwebsite.dao.ReservationDao;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPool;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPoolException;
import ru.myfirstwebsite.domain.to.Reservation;
import ru.myfirstwebsite.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLReservationDao implements ReservationDao {

    private static final String RESERVATION_ID = "idreservation";
    private static final String ROOM_ID =  "roomid";
    private static final String DATE_FROM = "datefrom";
    private static final String DATE_TO = "dateto";

    private static final String SELECT_RESERVATION_BY_ROOM = "SELECT * FROM reservation WHERE roomid = ?";
    private static final String SELECT_ALL_RESERVATIONS = "SELECT * FROM reservation";
    private static final String SELECT_BY_ID = "SELECT * FROM reservation WHERE idreservation = ?";
    private static final String DELETE_RESERVATION = "DELETE FROM reservation WHERE idreservation = ?";

    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private SQLReservationDao(){}

    public static ReservationDao getInstance() {
        return SQLReservationDao.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final ReservationDao instance = new SQLReservationDao();
    }

    @Override
    public boolean checkReservation(Integer roomId) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_RESERVATION_BY_ROOM)) {
            statement.setInt(1, roomId);
            ResultSet set = statement.executeQuery();

            return set.next();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public List<Reservation> findAll() throws DaoException {
        List<Reservation> list = new ArrayList<>();
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_RESERVATIONS)) {
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(set.getInt(RESERVATION_ID));
                reservation.setRoomId(set.getInt(ROOM_ID));
                reservation.setDateFrom(set.getDate(DATE_FROM));
                reservation.setDateTo(set.getDate(DATE_TO));
                list.add(reservation);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return list;
    }

    @Override
    public Reservation findById(Integer id) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                Reservation reservation = new Reservation();
                reservation.setReservationId(set.getInt(RESERVATION_ID));
                reservation.setRoomId(set.getInt(ROOM_ID));
                reservation.setDateFrom(set.getDate(DATE_FROM));
                reservation.setDateTo(set.getDate(DATE_TO));
                return reservation;
            } else {
                return null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public boolean delete(Integer id) throws DaoException {
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(DELETE_RESERVATION)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Integer create(Reservation entity) throws DaoException {
        return 0;
    }

    @Override
    public Integer update(Reservation entity) throws DaoException {
        return null;
    }
}
