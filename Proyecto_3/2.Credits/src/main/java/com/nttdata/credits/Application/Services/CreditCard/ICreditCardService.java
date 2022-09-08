package com.nttdata.credits.Application.Services.CreditCard;

import com.nttdata.credits.Domain.CreditCard;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICreditCardService {
    Flux<CreditCard> list();

    Mono<CreditCard> register(CreditCard creditCard);

    Mono<CreditCard> findById(String id);

    Mono<CreditCard> updater(String id, CreditCard creditCard);
}
