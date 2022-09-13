package io.github.dft.amazon.model.orders.v0;

import lombok.Data;

@Data
public class PaymentExecutionDetailItem {

    private Money payment;
    private String paymentMethod;
}