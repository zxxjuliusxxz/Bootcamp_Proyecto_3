package com.nttdata.pe.Customer.Infraestructure;

import com.nttdata.pe.Customer.Domain.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer, Long> {
}