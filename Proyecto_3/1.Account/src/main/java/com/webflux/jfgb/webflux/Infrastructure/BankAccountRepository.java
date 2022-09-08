package com.webflux.jfgb.webflux.Infrastructure;

import com.webflux.jfgb.webflux.Application.Models.DTO.HeadLineDTO;
import com.webflux.jfgb.webflux.Application.Models.DTO.SignatoriesDTO;
import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import com.webflux.jfgb.webflux.Domain.BankAccount;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Repository
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String> {
    Mono<BankAccount> findByNumber(String number);


}