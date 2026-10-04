package by.bseu.pp.lab03.model;

import java.time.LocalDate;

/**
 * Represents a payment connected with an order.
 */
public class Payment {
    private final String paymentId;
    private final double amount;
    private final LocalDate date;
    private PaymentStatus status;

    public Payment(String paymentId, double amount, LocalDate date) {
        // TODO: validate constructor arguments
        if (paymentId==null || paymentId.isBlank()) {
            throw new IllegalArgumentException();
        }
        if (amount<=0) {
            throw new IllegalArgumentException();
        }
        if (date==null) {
            throw new IllegalArgumentException();
        }
        this.paymentId = paymentId;
        this.amount = amount;
        this.date = date;
        this.status = PaymentStatus.CREATED;


    }

    public String getPaymentId() {
        return paymentId;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    public PaymentStatus getStatus() {
        return status;
    }

    public void markSuccessful() {
        // TODO: change CREATED payment to SUCCESSFUL
        if (this.status != PaymentStatus.CREATED) {
            throw new IllegalStateException("Повторная обработка платежа запрещена");
        }
        this.status = PaymentStatus.SUCCESSFUL;
    }

    public void markFailed() {
        // TODO: change CREATED payment to FAILED
        if (status != PaymentStatus.CREATED) {
            throw new IllegalStateException("Повторная обработка платежа запрещена");
        }
        this.status = PaymentStatus.FAILED;
    }

    public boolean isSuccessful() {
        // TODO: return true only for SUCCESSFUL payment
        return this.status == PaymentStatus.SUCCESSFUL;
    }
}
