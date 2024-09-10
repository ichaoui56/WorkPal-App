package org.workPall.entities;

import java.time.LocalDateTime;

public class Payment {
    private int id;
    private int amount;
    private LocalDateTime paymentDate;
    private Reservation reservation;

    public Payment(int id, int amount, LocalDateTime paymentDate, Reservation reservation) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.reservation = reservation;
    }


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public Reservation getReservation() {
        return reservation;
    }
    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                ", reservation=" + reservation +
                '}';
    }
}

