package com.webflux.jfgb.webflux.Controllers;

import com.webflux.jfgb.webflux.Application.Services.BankAccountMovement.IBankAccountMovementService;
import com.webflux.jfgb.webflux.Domain.BankAccountMovement;
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
@RequestMapping("/api/v1/bankAccountMovement")
public class BankAccountMovementController {

    @Autowired
    private IBankAccountMovementService bankAccountMovementService;

    @PostMapping(value = "/insert")
    public Mono<ResponseEntity<BankAccountMovement>> addMovement(@RequestBody BankAccountMovement bankAccountTransaction) {
        return bankAccountMovementService.register(bankAccountTransaction)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<Flux<BankAccountMovement>> getAccountBank() {
        return ResponseEntity.ok(bankAccountMovementService.list());
    }

    @GetMapping(value = "/getById/{id}")
    public Mono<ResponseEntity<BankAccountMovement>> getAccountBankById(@PathVariable String id) {
        return bankAccountMovementService.findById(id)
                .map(accountBank -> ResponseEntity.ok(accountBank))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}