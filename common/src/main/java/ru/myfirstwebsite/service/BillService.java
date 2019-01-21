package ru.myfirstwebsite.service;

import ru.myfirstwebsite.domain.to.Bill;

import java.util.List;

public interface BillService extends GenericServiceInterface <Bill, Integer> {
    Bill loadById(int billId) throws ServiceException;

    List<Bill> findByBill(Bill billId) throws  ServiceException;
}
