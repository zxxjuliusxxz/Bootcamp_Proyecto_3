package service.impl;

import com.webflux.jfgb.webflux.Application.Models.DTO.HeadLineDTO;
import com.webflux.jfgb.webflux.Application.Models.DTO.SignatoriesDTO;
import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import com.webflux.jfgb.webflux.Application.Services.BankAccount.BankAccountService;
import com.webflux.jfgb.webflux.Domain.BankAccount;
import com.webflux.jfgb.webflux.Infrastructure.Mock.IBankAccountMockRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
public class AccountServiceImplTest {

    @Mock
    IBankAccountMockRepository bankAccountRepository;

    @InjectMocks
    BankAccountService bankAccountService;

    @Autowired
    private Mono<BankAccount> account;

    @Autowired
    private BankAccount bankAccount;

    @Test
    void findAccountById() {

        List<HeadLineDTO> headLineList = new ArrayList<>();
        List<SignatoriesDTO> signatoriesList = new ArrayList<>();

        HeadLineDTO head = new HeadLineDTO();
        head.setId("14566987");
        headLineList.add(head);

        SignatoriesDTO sign = new SignatoriesDTO();
        sign.setId("27922456");
        signatoriesList.add(sign);

        Mockito.when(bankAccountService.findById("1")).thenReturn(account);
        Mono<BankAccount> obj = bankAccountService.findById("1");
        assertEquals(account, obj);
        account.subscribe(x -> assertEquals("1", x.getId()));
        account.subscribe(y -> assertEquals("Cuenta nueva 1", y.getDescription()));
        account.subscribe(z -> assertEquals("123456792", z.getNumber()));
        account.subscribe(z -> assertEquals(50000.00, z.getLimitAccount()));
        account.subscribe(z -> assertEquals(Long.parseLong("47922430"), z.getCustomerId()));
        account.subscribe(z -> assertEquals(CustomerTypesEnum.PERSONAL, z.getCustomerType()));

        account.subscribe(z -> assertEquals(headLineList, z.getHeadLineList()));
        account.subscribe(z -> assertEquals(signatoriesList, z.getSignatories()));
    }
}
