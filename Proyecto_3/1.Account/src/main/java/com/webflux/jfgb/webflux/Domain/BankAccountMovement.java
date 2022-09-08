package com.webflux.jfgb.webflux.Domain;

import com.webflux.jfgb.webflux.Application.Models.Enum.BanksAccountMovementTypeEnum;
import com.webflux.jfgb.webflux.Application.Models.Enum.BanksAccountTypeEnum;
import com.webflux.jfgb.webflux.Application.Models.Enum.CustomerTypesEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@Document(collection = "account_movements")
public class BankAccountMovement {

    @Id
    private String id;
    private String description;
    private Double amount; // Mínimo puede empezar de cero.
    private String bankAccountId;
    private BanksAccountMovementTypeEnum accountMovementType;
    private BanksAccountTypeEnum accountType;

    private Double commission;
    private CustomerTypesEnum customerType;

    public Double getCommissionSigned() {
        return (this.accountType == accountType.C_AHORRO ? this.commission = 0.0 :
                this.accountType == accountType.C_CORRIENTE ? -this.commission :
                this.accountType == accountType.C_PLAZOFIJO ? this.commission = 0.0 : null);
    }

    public static Double sumMovements(Double sum, BankAccountMovement movement){
        if (movement.getAccountMovementType() == BanksAccountMovementTypeEnum.DEPOSITO) {
            return sum + movement.getAmount();
        } else {
            return sum - movement.getAmount();
        }
    }

    public Double getAmountSigned() {
        return this.accountMovementType == accountMovementType.DEPOSITO ? this.amount : -this.amount;
    }
}