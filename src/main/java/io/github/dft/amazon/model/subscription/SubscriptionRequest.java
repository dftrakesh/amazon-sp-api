package io.github.dft.amazon.model.subscription;

import lombok.Data;

@Data
public class SubscriptionRequest {

    private String payloadVersion;
    private String destinationId;
}