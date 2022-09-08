package com.webflux.jfgb.webflux.Infrastructure.Mock;

import com.webflux.jfgb.webflux.Domain.BankAccount;
import reactor.core.publisher.Flux;

public interface IBankAccountMockRepository {
    Flux<BankAccount> listarAccount();
}