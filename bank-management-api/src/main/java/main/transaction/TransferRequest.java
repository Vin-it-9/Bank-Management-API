package main.transaction;

import io.micronaut.serde.annotation.Serdeable;

@Serdeable
public class TransferRequest {

    private String senderAccountNumber;
    private String receiverAccountNumber;
    private double amount;

    public String getSenderAccountNumber() {

        return senderAccountNumber;
    }

    public void setSenderAccountNumber(String senderAccountNumber) {
        this.senderAccountNumber = senderAccountNumber;
    }

    public String getReceiverAccountNumber() {
        return receiverAccountNumber;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}