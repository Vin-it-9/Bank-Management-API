package main.transaction;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import main.account.Account;
import main.account.AccountRepository;
import main.account.Accountservice;
import main.user.UserService;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Singleton
@Transactional
public class TransactionService {

    @Inject
    private TransactionRepository transactionRepository;

    @Inject
    private AccountRepository accountRepository;

    @Inject
    private UserService userService;

    @Inject
    private Accountservice accountService;


    @Transactional
    public String transferAmount(String senderAccountNumber, String receiverAccountNumber, double amount) {

        if (amount <= 0) {
            throw new IllegalArgumentException("Transfer amount must be greater than zero.");
        }

        Account senderAccount = accountRepository.findByAccountNumber(senderAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Sender account not found"));

        Account receiverAccount = accountRepository.findByAccountNumber(receiverAccountNumber)
                .orElseThrow(() -> new IllegalArgumentException("Receiver account not found"));

        if (senderAccount.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient balance in sender's account");
        }

        senderAccount.setBalance(senderAccount.getBalance() - amount);
        receiverAccount.setBalance(receiverAccount.getBalance() + amount);

        accountRepository.update(senderAccount);
        accountRepository.update(receiverAccount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTransactionDate(LocalDateTime.now());
        transaction.setAccount(senderAccount);
        transaction.setSenderId(senderAccount.getId());
        transaction.setReciverId(receiverAccount.getId());

        transactionRepository.save(transaction);

        return "Transaction completed successfully.";

    }



}
