package com.nttdata.credits.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "loan_banks")
public class LoanBank {
    @Id
    private String id;
    private String description;
    private Double amount;
    private int dues;
    private String customerId;
}
