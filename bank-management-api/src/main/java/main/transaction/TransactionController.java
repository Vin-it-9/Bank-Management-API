package main.transaction;

import io.micronaut.http.annotation.Controller;
import jakarta.inject.Inject;

@Controller("Transaction")
public class TransactionController {

    @Inject
    TransactionService transactionService;


}
