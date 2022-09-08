package com.nttdata.credits.Application.Services.CreditCard;

import com.nttdata.credits.Domain.CreditCard;
import com.nttdata.credits.Infraestructure.CreditCardRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.function.Function;

@Service
@Log4j2
public class CreditCardService implements ICreditCardService {

    private static final Logger logger = LogManager.getLogger(CreditCardService.class);
    @Autowired
    private CreditCardRepository creditRepository;

    @Override
    public Flux<CreditCard> list() {
        logger.info("Listado de Creditos");
        return creditRepository.findAll();
    }

    @Override
    public Mono<CreditCard> register(CreditCard creditCard) {
        logger.info("Registrando el credito: " + creditCard);
        Function<Boolean, Mono<CreditCard>> exits = (Boolean b) -> {
            if (b) {
                return Mono.error(new Exception("Credit card already registered"));
            } else {
                return creditRepository.save(creditCard);
            }
        };

        return creditRepository.findByNumber(creditCard.getNumber())
                .hasElement()
                .flatMap(exits);
    }

    @Override
    public Mono<CreditCard> findById(String id) {
        logger.info("Credito encontrado: " + creditRepository.findById(id));
        return creditRepository.findById(id);
    }

    @Override
    public Mono<CreditCard> updater(String id, CreditCard creditCard) {
        logger.info("Actualizando el credito: " + creditCard);
        return creditRepository.findById(id)
                .flatMap(existingCredit -> {
                    existingCredit.setDescription(creditCard.getDescription());
                    existingCredit.setLimitCredit(creditCard.getLimitCredit());
                    existingCredit.setNumber(creditCard.getNumber());
                    return creditRepository.save(existingCredit);
                });
    }
}