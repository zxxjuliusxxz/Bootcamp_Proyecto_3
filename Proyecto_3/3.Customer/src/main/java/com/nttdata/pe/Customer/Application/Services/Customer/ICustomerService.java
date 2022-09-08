package com.nttdata.pe.Customer.Application.Services.Customer;

import com.nttdata.pe.Customer.Domain.Customer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerService {
    Flux<Customer> list();
    Mono<Customer> register(Customer creditCard);
    Mono<Customer> findById(Long id);
    Mono<Customer> updater(Long id, Customer creditCard);
    Mono<Void> delete(Long id);
}