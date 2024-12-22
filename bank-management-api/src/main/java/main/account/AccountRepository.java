package main.account;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.jpa.repository.JpaRepository;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long> {

    List<Account> findByUserId(Long userId);

}
