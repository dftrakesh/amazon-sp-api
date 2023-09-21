package io.github.dft.amazon.model.subscription.v1;

import lombok.Data;

@Data
public class SubscriptionPayload {

    private String destinationId;
    private Float payloadVersion;
    private String subscriptionId;
}