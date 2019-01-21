package ru.myfirstwebsite.service.impl;

import ru.myfirstwebsite.dao.factory.DaoFactory;
import ru.myfirstwebsite.domain.to.Bill;
import ru.myfirstwebsite.service.BillService;
import ru.myfirstwebsite.service.ServiceException;

import java.util.List;

public class BillServiceimpl implements BillService {
    private static final DaoFactory FACTORY = DaoFactory.getDaoFactory();

    @Override
    public Bill loadById(int billId) throws ServiceException {
        return null;
    }

    @Override
    public List<Bill> findByBill(Bill billId) throws ServiceException {
        return null;
    }

    @Override
    public Integer create(Bill entity) throws com.google.protobuf.ServiceException {
        return null;
    }

    @Override
    public List<Bill> loadAll() throws com.google.protobuf.ServiceException {
        return null;
    }
}
