package com.webflux.jfgb.webflux.Application.Services.BankAccountMovement;

import com.webflux.jfgb.webflux.Domain.BankAccountMovement;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBankAccountMovementService {
    Mono<BankAccountMovement> register(BankAccountMovement creditCardMovement);
    Flux<BankAccountMovement> list();
    Mono<BankAccountMovement> findById(String id);
}