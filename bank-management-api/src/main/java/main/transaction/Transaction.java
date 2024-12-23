package main.transaction;

import io.micronaut.serde.annotation.Serdeable;
import jakarta.persistence.*;
import main.account.Account;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Serdeable
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;

    private LocalDateTime transactionDate;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private Long SenderId;

    private Long ReciverId;

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Long getSenderId() {
        return SenderId;
    }

    public void setSenderId(Long senderId) {
        SenderId = senderId;
    }

    public Long getReciverId() {
        return ReciverId;
    }

    public void setReciverId(Long reciverId) {
        ReciverId = reciverId;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
