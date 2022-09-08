package com.webflux.jfgb.webflux.Infrastructure;

import com.webflux.jfgb.webflux.Domain.BankAccountMovement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface BankAccountMovementRepository extends ReactiveMongoRepository<BankAccountMovement, String> {
    Flux<BankAccountMovement> findBankAccountMovementIdBy(String id);
}