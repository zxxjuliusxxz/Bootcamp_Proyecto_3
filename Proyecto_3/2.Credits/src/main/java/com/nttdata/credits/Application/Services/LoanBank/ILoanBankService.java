package com.nttdata.credits.Application.Services.LoanBank;

import com.nttdata.credits.Domain.LoanBank;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ILoanBankService {
    Flux<LoanBank> list();

    Mono<LoanBank> register(LoanBank loanBank);

    Mono<LoanBank> findById(String id);

    Mono<LoanBank> updater(String id, LoanBank loanBank);
}
