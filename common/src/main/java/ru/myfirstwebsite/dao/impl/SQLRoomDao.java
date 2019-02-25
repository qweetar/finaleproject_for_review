package ru.myfirstwebsite.dao.impl;

import ru.myfirstwebsite.dao.RoomDao;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPool;
import ru.myfirstwebsite.dao.connection_pool.ConnectionPoolException;
import ru.myfirstwebsite.domain.to.Room;
import ru.myfirstwebsite.exceptions.DaoException;
import ru.myfirstwebsite.exceptions.NoSuchEntityException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQLRoomDao implements RoomDao {

    private static final String ROOM_ID = "idroom";
    private static final String ROOM_CLASS = "class";
    private static final String ROOM_PRICE_PER_DAY = "priceday";
    private static final String ROOM_NUM = "room#";
    private static final String ROOM_SIZE = "roomsize";
    private static final String ROOM_STATUS = "roomstatus";

    private static final ConnectionPool POOL = ConnectionPool.getInstance();

    private static final String SELECT_BY_ID = "SELECT * FROM room WHERE idroom = ?";
    private static final String CREATE_ROOM = "INSERT INTO room (idroom, class, priceday, room#, roomsize, roomstatus)" +
            "VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SELECT_BY_ROOM_NUM = "SELECT * FROM room WHERE room# = ?";
    private static final String DELETE_ROOM = "DELETE FROM room WHERE idroom = ?";
    private static final String SELECT_ALL_ROOMS = "SELECT * FROM room";

    public SQLRoomDao() {
    }

    public static RoomDao getInstance() {
        return SQLRoomDao.SingletonHolder.instance;
    }

    private static class SingletonHolder {
        private static final RoomDao instance = new SQLRoomDao();
    }

    @Override
    public Room roomNode(Integer roomNum) throws DaoException, NoSuchEntityException {
        return null;
    }

    @Override
    public boolean checkRoom(Integer roomNum) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_BY_ROOM_NUM)) {
            statement.setInt(1, roomNum);
            ResultSet set = statement.executeQuery();

            return set.next();
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public List<Room> findAll() throws DaoException {
        List<Room> roomList = new ArrayList<>();
        try (Connection connect = POOL.getConnection();
             PreparedStatement statement = connect.prepareStatement(SELECT_ALL_ROOMS)) {
            ResultSet set = statement.executeQuery();

            while (set.next()) {
                Room room = new Room();
                room.setRoomId(set.getInt(ROOM_ID));
                room.setRoomNum(set.getInt(ROOM_NUM));
                room.setRoomSize(set.getInt(ROOM_SIZE));
                room.setRoomClass(set.getString(ROOM_CLASS));
                room.setRoomPricePerDay(set.getInt(ROOM_PRICE_PER_DAY));
                room.setRoomStatus(set.getString(ROOM_STATUS));
                roomList.add(room);
                }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
        return roomList;
    }

    @Override
    public Room findById(Integer id) throws DaoException {
        try(Connection connect = POOL.getConnection();
            PreparedStatement statement = connect.prepareStatement(SELECT_BY_ID)) {
            statement.setInt(1, id);
            ResultSet set = statement.executeQuery();

            if(set.next()) {
                Room room = new Room();
                room.setRoomId(set.getInt(ROOM_ID));
                room.setRoomClass(set.getString(ROOM_CLASS));
                room.setRoomPricePerDay(set.getInt(ROOM_PRICE_PER_DAY));
                room.setRoomNum(set.getInt(ROOM_NUM));
                room.setRoomSize(set.getInt(ROOM_SIZE));
                room.setRoomStatus(set.getString(ROOM_STATUS));
                return room;
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
             PreparedStatement statement = connect.prepareStatement(DELETE_ROOM)) {
            statement.setInt(1, id);
            statement.executeUpdate();
            return true;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Exception", e);
        }
    }

    @Override
    public Integer create(Room room) throws DaoException {
//        try(Connection connect = POOL.getConnection();
//            PreparedStatement statement = connect.prepareStatement(CREATE_ROOM)) {
//            statement.setInt(1, room.getRoomId());
//            statement.setString(2, room.getRoomClass());
//            statement.setInt(3, room.getRoomPrice());
//            statement.setInt(4, room.getRoomNum());
//            statement.setInt(5, room.getRoomSize());
//            statement.executeUpdate();
//            ResultSet set = statement.executeQuery();
//            set.next();
            return 1;
//        } catch (SQLException | ConnectionPoolException e) {
//            throw new DaoException("Exception", e);
//        }
    }

    @Override // Ask about this realisation of this method?????
    public Integer update(Room entity) throws DaoException {
        return null;
    }
}
