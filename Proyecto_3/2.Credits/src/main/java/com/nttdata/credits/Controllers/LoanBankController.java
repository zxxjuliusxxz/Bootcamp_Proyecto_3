package com.nttdata.credits.Controllers;

import com.nttdata.credits.Application.Services.LoanBank.ILoanBankService;
import com.nttdata.credits.Domain.LoanBank;
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
@RequestMapping("/api/v1/loanBanks")
public class LoanBankController {

    @Autowired
    private ILoanBankService ILoanBankService;

    @GetMapping
    public ResponseEntity<Flux<LoanBank>> getLoanBanks() {
        return ResponseEntity.ok(ILoanBankService.list());
    }

    @GetMapping(value = "/getById/{id}")
    public Mono<ResponseEntity<LoanBank>> getLoanBank(@PathVariable String id) {
        return ILoanBankService.findById(id)
                .map(loanBank -> ResponseEntity.ok(loanBank))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "/insert")
    public Mono<ResponseEntity<LoanBank>> addLoanBank(@RequestBody LoanBank loanBank) {
        return ILoanBankService.register(loanBank)
                .map(c -> ResponseEntity.status(HttpStatus.CREATED).body(c));
    }

    @PutMapping(value = "/update/{id}")
    public Mono<ResponseEntity<LoanBank>> updateLoanBank(@PathVariable String id, @RequestBody LoanBank loanBank) {
        return ILoanBankService.updater(id, loanBank)
                .map(mapper -> ResponseEntity.ok(mapper))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }
}