package com.nttdata.credits.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "credit_cards")
public class CreditCard {
    @Id
    private String id;
    private String description;
    @Indexed(unique = true)
    private String number;
    private Double limitCredit;
    private Long customerId;
}
