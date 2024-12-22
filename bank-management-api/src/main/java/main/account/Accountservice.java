package main.account;

import io.micronaut.core.annotation.NonNull;
import io.micronaut.http.HttpResponse;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import jakarta.transaction.Transactional;
import main.user.User;
import main.user.UserRepository;

import java.util.List;
import java.util.Optional;

@Singleton
@Transactional
public class Accountservice {

    @Inject
    UserRepository userRepository;

    @Inject
    AccountRepository accRepository;


    public Account createAccount(Account account, Long userId) {

        String randomAccountNumber = AccountUtils.generateRandomAccountNumber();
        account.setAccountNumber(randomAccountNumber);
        @NonNull Optional<User> user = userRepository.findById(userId);
        account.setUser(user.orElse(null));
        return accRepository.save(account);

    }

    public @NonNull Optional<Account> getAccountById(Long accountId) {
        return accRepository.findById(accountId);
    }

    public List<Account> getAccountByUserId(Long userId) {
        return accRepository.findByUserId(userId);
    }

    public List<Account> getAllAccounts() {
        return accRepository.findAll();
    }

    public Account updateAccount(Account account, Long accountId) {

        Optional<Account> existingAccount = accRepository.findById(accountId);

        if (existingAccount.isPresent()) {
            Account accountToUpdate = existingAccount.get();

            if (account.getAccountNumber() != null) {
                accountToUpdate.setAccountNumber(account.getAccountNumber());
            }

            if (account.getBalance() != 0) {
                accountToUpdate.setBalance(account.getBalance());
            }

            if (account.getUser() != null && account.getUser().getId() != null) {
                Optional<User> user = userRepository.findById(account.getUser().getId());
                accountToUpdate.setUser(user.orElse(null));
            }
            return accRepository.save(accountToUpdate);

        } else {
            return null;
        }
    }

    public HttpResponse<?> deleteAccount(Long accountId) {

        Optional<Account> account = accRepository.findById(accountId);

        if (account.isPresent()) {
            accRepository.deleteById(accountId);
            return HttpResponse.noContent();
        } else {
            return HttpResponse.notFound();
        }
    }

    public Account deposit(Long accountId, Long amount) {
        Optional<Account> account = accRepository.findById(accountId);
        if (account.isPresent()) {
            Account accountToUpdate = account.get();
            accountToUpdate.setBalance(accountToUpdate.getBalance() + amount);
            return accRepository.save(accountToUpdate);
        }
        return null;
    }

    public Account withdraw(Long accountId, Long amount) {
        Optional<Account> account = accRepository.findById(accountId);
        if (account.isPresent()) {
            Account accountToUpdate = account.get();
            accountToUpdate.setBalance(accountToUpdate.getBalance() - amount);
            return accRepository.save(accountToUpdate);
        }
        return null;
    }

    public double balance(Long accountId) {
        Optional<Account> account = accRepository.findById(accountId);
        return account.get().getBalance();
    }



}
