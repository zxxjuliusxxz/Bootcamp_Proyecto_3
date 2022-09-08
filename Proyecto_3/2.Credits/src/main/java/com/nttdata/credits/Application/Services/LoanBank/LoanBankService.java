package com.nttdata.credits.Application.Services.LoanBank;

import com.nttdata.credits.Application.Services.CreditCard.CreditCardService;
import com.nttdata.credits.Domain.LoanBank;
import com.nttdata.credits.Infraestructure.LoanBankRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class LoanBankService implements ILoanBankService {
    private static  Logger logger = LogManager.getLogger(CreditCardService.class);
    @Autowired
    private LoanBankRepository loanBankRepository;

    @Override
    public Flux<LoanBank> list() {
        return loanBankRepository.findAll();
    }

    @Override
    public Mono<LoanBank> register(LoanBank loanBank) {
        logger.info("Registrando movimiento de credito: " + loanBank);
        return loanBankRepository.save(loanBank);
    }

    @Override
    public Mono<LoanBank> findById(String id) {
        logger.info("Movimiento de credito encontrado: " + loanBankRepository.findById(id));
        return loanBankRepository.findById(id);
    }

    @Override
    public Mono<LoanBank> updater(String id, LoanBank loanBank) {
        logger.info("Actualizando el movimiento de credito: " + loanBank);
        return loanBankRepository.findById(id)
                .flatMap(existingLoanBank -> {
                    existingLoanBank.setDescription(loanBank.getDescription());
                    existingLoanBank.setAmount(loanBank.getAmount());
                    existingLoanBank.setDues(loanBank.getDues());
                    existingLoanBank.setCustomerId(loanBank.getCustomerId());
                    return loanBankRepository.save(existingLoanBank);
                });
    }
}
