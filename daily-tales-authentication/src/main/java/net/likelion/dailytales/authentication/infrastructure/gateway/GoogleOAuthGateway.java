package net.likelion.dailytales.authentication.infrastructure.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthGateway;
import net.likelion.dailytales.authentication.application.OAuthResource;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GoogleOAuthGateway implements OAuthGateway {
    @Value("${oauth2.client.google.resource-uri}")
    private String resourceUri;

    private final RestClient restClient;

    @Override
    public OAuthResource authenticate(String accessToken) {
        GoogleResourceResponse resourceResponse = requestResource(accessToken);
        return OAuthResource.of(OAuthType.GOOGLE, resourceResponse.id());
    }

    private GoogleResourceResponse requestResource(String accessToken) {
        ResponseEntity<GoogleResourceResponse> result = restClient
                .get()
                .uri(resourceUri)
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .toEntity(GoogleResourceResponse.class);
        if (result.getStatusCode().isError() || result.getBody() == null) {
            throw new AuthenticationFailedException();
        }
        return result.getBody();
    }

    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    private record GoogleResourceResponse(
            String id
    ) {
    }

    @Override
    public OAuthType getType() {
        return OAuthType.GOOGLE;
    }
}