package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.BillDao;
import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Bill;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.service.BillService;
import ru.myfirstwebsite.service.ServiceException;

import java.util.List;

public class BillServiceImpl implements BillService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();

    private BillServiceImpl(){}

    public static BillService getInstance(){
        return SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final BillService instance = new BillServiceImpl();
    }

    @Override
    public Bill loadById(int billId) throws ServiceException {
        try {
            BillDao billDao = FACTORY.getBillDao();
            return billDao.findById(billId);

        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public List<Bill> findByBill(Bill billId) throws ServiceException {
        return null;
    }

    @Override
    public Bill create(Bill bill) throws ServiceException {
        try {
            BillDao billDao = FACTORY.getBillDao();
            int billId = (int) billDao.create(bill);

            if(billId == 0) {
                return null;
            } else {
                bill.setBillId(billId);
                return bill;
            }
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }

    @Override
    public List<Bill> loadAll() throws ServiceException {
        List<Bill> list;
        try {
            BillDao billDao = FACTORY.getBillDao();
            list = billDao.findAll();
            return list;
        } catch (DaoException e) {
            throw new ServiceException("Service Exception", e);
        }
    }
}
