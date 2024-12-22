package main.account;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import jakarta.inject.Inject;
import main.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Controller("/Account")
public class AccountController {

    @Inject
    UserRepository userRepository;

    @Inject
    Accountservice accountservice;

    @Post("/create/{user_id}")
    public HttpResponse<Account> createacc(@PathVariable long user_id, @Body Account account) {
        Account createdAccount = accountservice.createAccount(account, user_id);
        if (createdAccount != null) {
            return HttpResponse.created(createdAccount);
        }
        return HttpResponse.badRequest();
    }

    @Get("/{id}")
    public @NonNull Optional<Account> getAccountById(@PathVariable long id) {
        return accountservice.getAccountById(id);
    }

    @Get("/user/{id}")
    public List<Account> getUserAccounts(@PathVariable long id) {
        return accountservice.getAccountByUserId(id);
    }

    @Get("/all")
    public List<Account> getAllAccounts() {
        return accountservice.getAllAccounts();
    }

    @Put("/update/{account_id}")
    public HttpResponse<Account> updateAccount(@PathVariable long account_id, @Body Account account) {
        Account updatedAccount = accountservice.updateAccount(account, account_id);
        if (updatedAccount != null) {
            return HttpResponse.ok(updatedAccount);
        } else {
            return HttpResponse.notFound();
        }
    }

    @Delete("/{account_id}")
    public HttpResponse<?> deleteAccount(@PathVariable long account_id) {
        return accountservice.deleteAccount(account_id);
    }


    @Put("/deposit/{account_id}")
    public Account deposit(@PathVariable Long account_id, @Body Long amount) {
        return accountservice.deposit(account_id,amount);
    }

    @Put("/withdraw/{account_id}")
    public Account withdraw(@PathVariable Long account_id, @Body Long amount) {
        return accountservice.withdraw(account_id,amount);
    }

    @Get("/balance/{account_id}")
    public double getBalance(@PathVariable long account_id) {
        return accountservice.balance(account_id);
    }


}
