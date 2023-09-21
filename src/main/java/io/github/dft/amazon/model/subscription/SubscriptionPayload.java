package io.github.dft.amazon.model.subscription;

import lombok.Data;

@Data
public class SubscriptionPayload {

    private String destinationId;
    private Float payloadVersion;
    private String subscriptionId;
}