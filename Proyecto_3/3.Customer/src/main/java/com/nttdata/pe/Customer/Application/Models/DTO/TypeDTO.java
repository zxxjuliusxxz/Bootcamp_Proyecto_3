package com.nttdata.pe.Customer.Application.Models.DTO;
import com.nttdata.pe.Customer.Application.Models.Enum.CustomerTypesEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
@AllArgsConstructor
public class TypeDTO {
    private Integer idType; // 1: PERSONAL y 2: EMPRESARIAL
    private CustomerTypesEnum customerType;
}