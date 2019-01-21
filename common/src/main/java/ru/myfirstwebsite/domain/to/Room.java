package ru.myfirstwebsite.domain.to;

import java.util.Objects;

public class Room {
    private int roomId;
    private String roomClass;
    private int roomPrice;
    private int roomNum;
    private int roomSize;

    public Room() {
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public String getRoomClass() {
        return roomClass;
    }

    public void setRoomClass(String roomClass) {
        this.roomClass = roomClass;
    }

    public int getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice) {
        this.roomPrice = roomPrice;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public int getRoomSize() {
        return roomSize;
    }

    public void setRoomSize(int roomSize) {
        this.roomSize = roomSize;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId &&
                roomPrice == room.roomPrice &&
                roomNum == room.roomNum &&
                roomSize == room.roomSize &&
                Objects.equals(roomClass, room.roomClass);
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomClass, roomPrice, roomNum, roomSize);
    }

    @Override
    public String toString() {
        return "Room{" +
                "roomId=" + roomId +
                ", roomClass='" + roomClass + '\'' +
                ", roomPrice=" + roomPrice +
                ", roomNum=" + roomNum +
                ", roomSize=" + roomSize +
                '}';
    }
}
