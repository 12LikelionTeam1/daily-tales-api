package net.likelion.dailytales.authentication.infrastructure.gateway;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthGateway;
import net.likelion.dailytales.authentication.application.OAuthResource;
import net.likelion.dailytales.authentication.application.OAuthType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestClient;
import org.springframework.http.ResponseEntity;

@Component
@RequiredArgsConstructor
public class GoogleOAuthGateway implements OAuthGateway {
    @Value("${oauth2.client.google.client-id}")
    private String clientId;

    @Value("${oauth2.client.google.client-secret}")
    private String clientSecret;

    @Value("${oauth2.client.google.redirect-uri}")
    private String redirectUri;

    @Value("${oauth2.client.google.token-uri}")
    private String tokenUri;

    @Value("${oauth2.client.google.resource-uri}")
    private String resourceUri;

    private final RestClient restClient;

    @Override
    public OAuthResource authenticate(String code) {
        GoogleTokenResponse tokenResponse = requestToken(code);
        GoogleResourceResponse resourceResponse = requestResource(tokenResponse.accessToken());

        return OAuthResource.of(OAuthType.GOOGLE, resourceResponse.id());
    }

    private GoogleTokenResponse requestToken(String code) {
        LinkedMultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", clientId);
        params.add("redirect_uri", redirectUri);
        params.add("code", code);
        params.add("client_secret", clientSecret);

        ResponseEntity<GoogleTokenResponse> result = restClient
                .post()
                .uri(tokenUri)
                .headers(headers -> headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED))
                .body(params)
                .retrieve()
                .toEntity(GoogleTokenResponse.class);
        if (result.getStatusCode().isError() || result.getBody() == null) {
            throw new RuntimeException("Failed to request token");
        }
        return result.getBody();
    }

    private GoogleResourceResponse requestResource(String accessToken) {
        ResponseEntity<GoogleResourceResponse> result = restClient
                .get()
                .uri(resourceUri)
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .toEntity(GoogleResourceResponse.class);
        if (result.getStatusCode().isError() || result.getBody() == null) {
            throw new RuntimeException("Failed to request resource");
        }
        return result.getBody();
    }

    @Override
    public OAuthType getType() {
        return OAuthType.GOOGLE;
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private record GoogleTokenResponse(
            String accessToken
    ) {
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private record GoogleResourceResponse(
            String id
    ) {
    }
}