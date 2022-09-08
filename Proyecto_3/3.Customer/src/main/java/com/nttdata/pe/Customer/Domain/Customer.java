package com.nttdata.pe.Customer.Domain;

import com.nttdata.pe.Customer.Application.Models.DTO.TypeDTO;
import com.nttdata.pe.Customer.Application.Models.Enum.CustomerTypesEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "customer")
public class Customer {
    @Id
    @Indexed(unique = true)
    private Long ruc_dni;
    private String name;
    private TypeDTO types;
}