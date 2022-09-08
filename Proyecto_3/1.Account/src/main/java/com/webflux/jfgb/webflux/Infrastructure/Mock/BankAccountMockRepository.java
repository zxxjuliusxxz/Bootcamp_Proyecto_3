package com.webflux.jfgb.webflux.Infrastructure.Mock;

import com.webflux.jfgb.webflux.Application.Models.DTO.HeadLineDTO;
import com.webflux.jfgb.webflux.Application.Models.DTO.SignatoriesDTO;
import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import com.webflux.jfgb.webflux.Domain.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

public class BankAccountMockRepository implements IBankAccountMockRepository {

    @Override
    public Flux<BankAccount> listarAccount() {
        List<HeadLineDTO> headLineList = new ArrayList<>();
        List<SignatoriesDTO> signatoriesList = new ArrayList<>();

        HeadLineDTO head = new HeadLineDTO();
        head.setId("14566987");
        headLineList.add(head);

        SignatoriesDTO sign = new SignatoriesDTO();
        sign.setId("27922456");
        signatoriesList.add(sign);

        BankAccount bankAccount_1 = new BankAccount();
        bankAccount_1.setId("1");
        bankAccount_1.setDescription("Cuenta nueva 1");
        bankAccount_1.setNumber("123456792");
        bankAccount_1.setLimitAccount(50000.00);
        bankAccount_1.setCustomerId(Long.parseLong("47922430"));
        bankAccount_1.setCustomerType(CustomerTypesEnum.PERSONAL);
        bankAccount_1.setHeadLineList(headLineList);
        bankAccount_1.setSignatories(signatoriesList);

        BankAccount bankAccount_2 = new BankAccount();
        bankAccount_2.setId("2");
        bankAccount_2.setDescription("Cuenta nueva 2");
        bankAccount_2.setNumber("423456793");
        bankAccount_2.setLimitAccount(23000.00);
        bankAccount_2.setCustomerId(Long.parseLong("77922323"));
        bankAccount_2.setCustomerType(CustomerTypesEnum.EMPRESARIAL);
        bankAccount_2.setHeadLineList(new ArrayList<HeadLineDTO>());
        bankAccount_2.setSignatories(new ArrayList<SignatoriesDTO>());

        Flux<BankAccount> bankAccountList = Flux.merge(Mono.just(bankAccount_1), Mono.just(bankAccount_2));
        bankAccountList.subscribe(x -> System.out.println(x));

        return bankAccountList;
    }
}