package io.github.dft.amazon.model.fulfillmentoutbound.v20200701;


import io.github.dft.amazon.model.orders.v0.Money;
import lombok.Data;

@Data
public class CODSettings {
    private Boolean isCod;
    private Money codCharge;
    private Money codChargeTax;
    private Money shippingCharge;
    private Money shippingChargeTax;
}