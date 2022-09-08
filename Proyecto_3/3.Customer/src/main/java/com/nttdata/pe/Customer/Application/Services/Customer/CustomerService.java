package com.nttdata.pe.Customer.Application.Services.Customer;

import com.nttdata.pe.Customer.Domain.Customer;
import com.nttdata.pe.Customer.Infraestructure.CustomerRepository;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class CustomerService implements ICustomerService {
    private static final Logger logger = LogManager.getLogger(CustomerService.class);
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Flux<Customer> list() {
        logger.info("Listado de clientes");
        return customerRepository.findAll();
    }

    @Override
    public Mono<Customer> register(Customer customer) {
        logger.info("Registrando el cliente: " + customer);
        return customerRepository.save(customer);
    }

    @Override
    public Mono<Customer> findById(Long id) {

        customerRepository.findById(id).flatMap(x->{
            if(x.getRuc_dni() != null){
                logger.info("Cliente encontrado con dni_ruc: " + customerRepository.findById(id));
                return Mono.just(x);
            }
            else {
                logger.info("Cliente no encontrado: ");
                return  Mono.justOrEmpty(x);
            }
        });



        return customerRepository.findById(id);
    }

    @Override
    public Mono<Customer> updater(Long id, Customer customer) {
        logger.info("Actualizando el cliente: " + customer);
        return customerRepository.findById(id)
                .flatMap(existingCredit -> {
                    existingCredit.setName(customer.getName());
                    existingCredit.setTypes(customer.getTypes());
                    return customerRepository.save(existingCredit);
                });
    }

    @Override
    public Mono<Void> delete(Long id){
        logger.info("Eliminado el usuario con ruc_dni: " + id);
        return customerRepository.deleteById(id);
    }
}