package com.webflux.jfgb.webflux.Application.Models.DTO;
import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import lombok.Data;
import lombok.ToString;
@ToString
@Data
public class TypeDTO {
    private Integer idType; // 1: PERSONAL y 2: EMPRESARIAL
    private CustomerTypesEnum customerType;
}