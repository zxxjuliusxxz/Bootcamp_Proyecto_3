package com.webflux.jfgb.webflux.Domain;

import com.webflux.jfgb.webflux.Application.Models.DTO.HeadLineDTO;
import com.webflux.jfgb.webflux.Application.Models.DTO.SignatoriesDTO;
import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@Document(collection = "account")
public class BankAccount {
    @Id
    private String id; // Código de la cuenta.
    private String description; // Descripción de la cuenta.
    @Indexed(unique = true)
    private String number; // Número de cuenta.
    private Double limitAccount; // Saldo límite o máximo.
    private Long customerId; // Código del Cliente (RUC_DNI).
    private CustomerTypesEnum customerType;
    private List<HeadLineDTO> headLineList; // TITULARES.
    private List<SignatoriesDTO> signatories; // FIRMANTES AUTORIZADOS.
    public BankAccount(){
        headLineList = new ArrayList<>();
        signatories = new ArrayList<>();
    }
}