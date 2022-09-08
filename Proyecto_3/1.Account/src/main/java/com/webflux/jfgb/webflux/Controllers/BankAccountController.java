package com.webflux.jfgb.webflux.Controllers;

import com.webflux.jfgb.webflux.Application.Models.DTO.CustomerDTO;
import com.webflux.jfgb.webflux.Application.Services.BankAccount.IBankAccountService;
import com.webflux.jfgb.webflux.Domain.BankAccount;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@Getter(AccessLevel.PROTECTED)
@RestController
@RequestMapping("/api/v1/bankAccount")
public class BankAccountController {

    @Autowired
    private IBankAccountService accountService;

    @GetMapping(value = "/getAll")
    public ResponseEntity<Flux<BankAccount>> getAccountBank() {
        return ResponseEntity.ok(accountService.list());
    }

    @PostMapping(value = "/insert")
    public Mono<ResponseEntity<BankAccount>> addAccount(@RequestBody BankAccount bankAccount) {
            return accountService.register(bankAccount)
                    .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c));
    }

    @GetMapping("/getById/{id}")
    public Mono<ResponseEntity<BankAccount>> getAccountBank(@PathVariable String id) {
        return accountService.findById(id)
                .map(accountBank -> ResponseEntity.ok(accountBank))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PutMapping("/update/{id}")
    public Mono<ResponseEntity<BankAccount>> updateAccountBank(@PathVariable String id,
        @RequestBody BankAccount bankAccount) {
        return accountService.updater(id, bankAccount)
                .map(mapper -> ResponseEntity.ok(mapper))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/customerById/{id}")
    @CircuitBreaker(name="api/v1/customer", fallbackMethod = "fallBackGetCustomerById")
    public Mono<ResponseEntity<CustomerDTO>> getCustomerById(@PathVariable Long id) {
        return accountService.findByIdCustomer(id)
                .map(customerObject -> ResponseEntity.ok(customerObject))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    public Mono<ResponseEntity<String>> fallBackGetCustomerById(Long id, RuntimeException runtimeException) {
        String msj = "El Microservicio Customer no esta respondiendo";
        Mono<String> msjMono = Mono.just(msj);
        return msjMono.map(c -> ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(c));
    }
}