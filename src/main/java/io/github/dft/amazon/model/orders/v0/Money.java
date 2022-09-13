package io.github.dft.amazon.model.orders.v0;

import lombok.Data;

@Data
public class Money {

    private String currencyCode;
    private String amount;
}