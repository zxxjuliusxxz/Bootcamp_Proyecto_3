package com.nttdata.credits.Application.Services.CreditCardMovement;

import com.nttdata.credits.Domain.CreditCardMovement;
import reactor.core.publisher.Mono;

public interface ICreditCardMovementService {
        Mono<CreditCardMovement> register(CreditCardMovement creditCardMovement);
}
