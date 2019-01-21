package ru.myfirstwebsite.dao.impl;

import ru.myfirstwebsite.dao.ApplicationDao;
import ru.myfirstwebsite.dao.BillDao;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPool;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPoolException;
import ru.myfirstwebsite.domain.to.Bill;
import ru.myfirstwebsite.exceptions.DaoException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLBillDao implements BillDao {

    private static final String RESERVATION_ID = "reservationId";
    private static final String PRICE = "price";
    private static final String BILL_ID = "idbill";
    private static final String ROOM_ID = "roomid";

    private static final String SELECT_ALL_BILLS = "SELECT * FROM bill";
    private static final String SELECT_BY_ID = "SELECT * FROM bill WHERE idbill = ?";
    private static final String DELETE_BILL = "DELETE FROM bill WHERE idbill = ?";
    private static final String SELECT_BILL_ROOM = "SELECT * FROM bill WHERE roomid = ?";

    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private SQLBillDao(){}

    public static BillDao getInstance() {
        return SQLBillDao.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final BillDao instance = new SQLBillDao();
    }

    @Override
    public List<Bill> getBill(Integer roomId) throws DaoException {
        List<Bill> list = new ArrayList<>();
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BILL_ROOM)) {
            statement.setInt(1, roomId);
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Bill bill = new Bill();
                bill.setBillId(set.getInt(BILL_ID));
                bill.setBillId(set.getInt(RESERVATION_ID));
                bill.setBillId(set.getInt(PRICE));
                bill.setBillId(set.getInt(ROOM_ID));
                list.add(bill);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("DAOException", e);
        }
        return list;
    }

    @Override
    public List<Bill> findAll() throws DaoException {
        List<Bill> list = new ArrayList<>();
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_BILLS)) {
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Bill bill = new Bill();
                bill.setBillId(set.getInt(BILL_ID));
                bill.setBillId(set.getInt(RESERVATION_ID));
                bill.setBillId(set.getInt(PRICE));
                bill.setBillId(set.getInt(ROOM_ID));
                list.add(bill);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return list;
    }

    @Override
    public Bill findById(Integer id) throws DaoException {
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if (set.next()) {
                Bill bill = new Bill();
                bill.setBillId(set.getInt(BILL_ID));
                bill.setBillId(set.getInt(RESERVATION_ID));
                bill.setBillId(set.getInt(PRICE));
                bill.setBillId(set.getInt(ROOM_ID));
                return bill;
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
             PreparedStatement statement = connect.prepareStatement(DELETE_BILL)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public int create(Bill entity) throws DaoException {
        return 0;
    }

    @Override
    public Integer update(Bill entity) throws DaoException {
        return null;
    }
}
