package com.webflux.jfgb.webflux.Infrastructure;

import com.webflux.jfgb.webflux.Domain.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;
@Repository
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String> {
    Mono<BankAccount> findByNumber(String number);


}