package com.webflux.jfgb.webflux.Application.Services.BankAccount;

import com.webflux.jfgb.webflux.Application.Models.DTO.CustomerDTO;
import com.webflux.jfgb.webflux.Domain.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IBankAccountService {
    Flux<BankAccount> list();

    Mono<BankAccount> register(BankAccount creditCard);

    Mono<BankAccount> findById(String id);

    Mono<BankAccount> updater(String id, BankAccount creditCard);

    Mono<CustomerDTO> findByIdCustomer(Long ruc_dni);


}