package com.nttdata.credits.Infraestructure;

import com.nttdata.credits.Domain.LoanBank;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface LoanBankRepository extends ReactiveMongoRepository<LoanBank, String> {
}