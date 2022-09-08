package com.nttdata.credits.Application.Services.CreditCardMovement;

import com.nttdata.credits.Application.Services.CreditCard.CreditCardService;
import com.nttdata.credits.Domain.CreditCard;
import com.nttdata.credits.Domain.CreditCardMovement;
import com.nttdata.credits.Infraestructure.CreditCardMovementRepository;
import com.nttdata.credits.Infraestructure.CreditCardRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.util.function.Function;

@Service
@Log4j2
public class CreditCardMovementService implements ICreditCardMovementService {
    private static final Logger logger = LogManager.getLogger(CreditCardService.class);
    @Autowired
    private CreditCardMovementRepository creditCardMovementRepository;

    @Autowired
    private CreditCardRepository creditCardRepository;

    @Override
    public Mono<CreditCardMovement> register(CreditCardMovement creditCardMovement) {
        logger.info("Registrando movimiento de credito: " + creditCardMovement);
        Function<CreditCard, Mono<CreditCardMovement>> validate = (creditCard) -> {

            return creditCardMovementRepository.findCreditCardIdBy(creditCard.getId())
                    .reduce(creditCard.getLimitCredit() + creditCardMovement.getAmountSigned(),
                            CreditCardMovement::sumMovements)
                    .flatMap(amount -> {
                        if (amount < 0) {
                            return Mono.error(new Exception("Credit card movement exceeds limit"));
                        } else {
                            return creditCardMovementRepository.save(creditCardMovement);
                        }
                    });

        };

        return creditCardRepository.findById(creditCardMovement.getCreditCardId())
                .flatMap(validate)
                .switchIfEmpty(Mono.error(new Exception("Credit card not found")));
    }
}