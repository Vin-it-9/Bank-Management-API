package main.transaction;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;

@Controller("/Transactions")
public class TransactionController {

    @Inject
    TransactionService transactionService;

    @Post("/transfer")
    public String transferAmount(@Body TransferRequest transferRequest) {
        return transactionService.transferAmount(
                transferRequest.getSenderAccountNumber(),
                transferRequest.getReceiverAccountNumber(),
                transferRequest.getAmount()
        );
    }




}
