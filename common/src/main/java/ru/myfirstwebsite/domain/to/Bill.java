package ru.myfirstwebsite.domain.to;

import java.util.Objects;

public class Bill {
    private int billId;
    private int price;
    private int reservationId;
    private int roomId;

    public Bill() {
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bill bill = (Bill) o;
        return getBillId() == bill.getBillId() &&
                getPrice() == bill.getPrice() &&
                getReservationId() == bill.getReservationId() &&
                getRoomId() == bill.getRoomId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getBillId(), getPrice(), getReservationId(), getRoomId());
    }

    @Override
    public String toString() {
        return "Bill{" +
                "billId=" + billId +
                ", price=" + price +
                ", reservationId=" + reservationId +
                ", roomId=" + roomId +
                '}';
    }
}
