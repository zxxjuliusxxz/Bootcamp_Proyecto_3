package com.webflux.jfgb.webflux.Application.Services.BankAccountMovement;

import com.webflux.jfgb.webflux.Application.Models.Enum.BanksAccountTypeEnum;
import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import com.webflux.jfgb.webflux.Application.Services.BankAccount.BankAccountService;
import com.webflux.jfgb.webflux.Domain.BankAccount;
import com.webflux.jfgb.webflux.Domain.BankAccountMovement;
import com.webflux.jfgb.webflux.Infrastructure.BankAccountMovementRepository;
import com.webflux.jfgb.webflux.Infrastructure.BankAccountRepository;
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
public class BankAccountMovementService implements IBankAccountMovementService {
    private static final Logger logger = LogManager.getLogger(BankAccountService.class);
    @Autowired
    private BankAccountMovementRepository bankAccountMovementRepository;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public Mono<BankAccountMovement> register(BankAccountMovement bankAccountMovement) {
        logger.info("Registrando el movimiento de cuenta bancaria: " + bankAccountMovement);
        Function<BankAccount, Mono<BankAccountMovement>> validate = (bankAccount) -> {

            return bankAccountMovementRepository.findBankAccountMovementIdBy(bankAccount.getId())
                    .reduce(bankAccount.getLimitAccount() + bankAccountMovement.getAmountSigned(),
                            BankAccountMovement::sumMovements)
                    .flatMap(amount -> {
                        if (amount < 0) {
                            return Mono.error(new Exception("Account bank movement exceeds limit"));
                        }

                        if(
                                (bankAccountMovement.getCommission() == 0.00) &&
                                (bankAccountMovement.getAccountType().equals(BanksAccountTypeEnum.C_AHORRO) || bankAccountMovement.getAccountType().equals(BanksAccountTypeEnum.C_PLAZOFIJO)) &&
                                        (!bankAccountMovement.getCustomerType().equals(CustomerTypesEnum.EMPRESARIAL))
                        ){
                            return bankAccountMovementRepository.save(bankAccountMovement);
                        }

                        if(bankAccountMovement.getAccountType().equals(BanksAccountTypeEnum.C_CORRIENTE)
                        ){
                            return bankAccountMovementRepository.save(bankAccountMovement);
                        }

                        else {
                            return Mono.error(new Exception("Unauthorized Bank Account"));
                        }

                    });
        };
        return bankAccountRepository.findById(bankAccountMovement.getBankAccountId())
                .flatMap(validate)
                .switchIfEmpty(Mono.error(new Exception("Account bank not found")));
    }
    @Override
    public Mono<BankAccountMovement> findById(String id) {
        logger.info("Movimiento de cuenta bancaria encontrada: " + bankAccountRepository.findById(id));
        return bankAccountMovementRepository.findById(id);
    }
    @Override
    public Flux<BankAccountMovement> list() {
        logger.info("Listado de movimientos de cuentas bancarias");
        return bankAccountMovementRepository.findAll();
    }
}