package com.webflux.jfgb.webflux.Application.Models.DTO;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class CustomerDTO {

    private Long ruc_dni;
    private String name;
    private TypeDTO types;
}
