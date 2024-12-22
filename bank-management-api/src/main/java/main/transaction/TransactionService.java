package main.transaction;


import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;

@Singleton
@Transactional
public class TransactionService {

    @Inject
    private TransactionRepository transactionRepository;


}
