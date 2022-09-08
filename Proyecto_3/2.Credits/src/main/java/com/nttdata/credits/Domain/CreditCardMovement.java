package com.nttdata.credits.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "credit_card_movements")
public class CreditCardMovement {
    private String id;
    private String description;
    private Double amount;
    private String creditCardId;
    private MovementType type;

    public static Double sumMovements(Double sum, CreditCardMovement movement) {

        if (movement.getType() == MovementType.CREDIT) {
            return sum - movement.getAmount();
        } else {
            return sum + movement.getAmount();
        }
    }

    public Double getAmountSigned() {
        return this.type == MovementType.DEBIT ? this.amount : -this.amount;
    }
}
