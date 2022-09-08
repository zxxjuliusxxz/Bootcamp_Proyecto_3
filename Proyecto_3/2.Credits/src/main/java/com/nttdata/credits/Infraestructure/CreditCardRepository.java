package com.nttdata.credits.Infraestructure;

import com.nttdata.credits.Domain.CreditCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CreditCardRepository extends ReactiveMongoRepository<CreditCard, String> {
   Mono<CreditCard> findByNumber(String number);
}
