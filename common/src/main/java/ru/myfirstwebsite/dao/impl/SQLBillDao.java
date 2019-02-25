package ru.myfirstwebsite.dao.impl;

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

    private static final String APPLICATION_ID = "applicationId";
    private static final String PRICE = "price";
    private static final String BILL_ID = "idbill";
    private static final String USER_ID = "userid";

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
                bill.setApplicationId(set.getInt(APPLICATION_ID));
                bill.setPrice(set.getInt(PRICE));
                bill.setUserId(set.getInt(USER_ID));
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
                bill.setApplicationId(set.getInt(APPLICATION_ID));
                bill.setPrice(set.getInt(PRICE));
                bill.setUserId(set.getInt(USER_ID));
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
                bill.setApplicationId(set.getInt(APPLICATION_ID));
                bill.setPrice(set.getInt(PRICE));
                bill.setUserId(set.getInt(USER_ID));
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
    public Integer create(Bill entity) throws DaoException {
        return 0;
    }

    @Override
    public Integer update(Bill entity) throws DaoException {
        return null;
    }
}
