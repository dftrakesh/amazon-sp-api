package io.github.dft.amazon.model.subscription.v1;

import lombok.Data;

@Data
public class SubscriptionRequest {

    private String payloadVersion;
    private String destinationId;
}