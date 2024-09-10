package org.workPall.entities;

import java.time.LocalDateTime;

public class Payment {
    private Long id;
    private double amount;
    private LocalDateTime paymentDate;
    private Reservation reservation;

    public Payment(Long id, double amount, LocalDateTime paymentDate, Reservation reservation) {
        this.id = id;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.reservation = reservation;
    }


    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }
    public void setAmount(double amount) {
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

