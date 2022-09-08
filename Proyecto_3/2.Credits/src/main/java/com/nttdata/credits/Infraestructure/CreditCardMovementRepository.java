package com.nttdata.credits.Infraestructure;

import com.nttdata.credits.Domain.CreditCardMovement;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CreditCardMovementRepository extends ReactiveMongoRepository<CreditCardMovement, String> {
    Flux<CreditCardMovement> findCreditCardIdBy(String id);
}