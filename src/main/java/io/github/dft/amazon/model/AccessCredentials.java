package io.github.dft.amazon.model;

import lombok.Data;

import java.util.List;

@Data
public class AccessCredentials {
    private String user;
    private String accessKeyId;
    private String secretAccessKey;
    private String userArn;
    private String roleArn;
    private String appId;
    private String clientIdentifier;
    private String redirectUrl;
    private String clientSecret;
    private String region;
    private String merchantId;
    private String accessToken;
    private String refreshToken;
    private String tokenType;
    private long expiresInTime;
    private String spAPIEndpoint;
    private List<String> marketplaces;
}
