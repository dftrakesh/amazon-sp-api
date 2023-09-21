package io.github.dft.amazon.model.subscription.v1;

import io.github.dft.amazon.model.error.Error;
import lombok.Data;

import java.util.List;

@Data
public class SubscriptionWrapper {

    private SubscriptionPayload payload;
    private List<Error> errors;
}