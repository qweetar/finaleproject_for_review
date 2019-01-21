package ru.myfirstwebsite.dao;

import ru.myfirstwebsite.domain.to.Bill;
import ru.myfirstwebsite.exceptions.DaoException;

import java.util.List;

public interface BillDao extends GenericDao <Bill, Integer> {


    List<Bill> getBill(Integer roomId) throws DaoException;
}
