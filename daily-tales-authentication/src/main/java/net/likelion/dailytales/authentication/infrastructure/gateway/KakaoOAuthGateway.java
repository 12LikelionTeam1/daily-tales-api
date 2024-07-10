package net.likelion.dailytales.authentication.infrastructure.gateway;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthGateway;
import net.likelion.dailytales.authentication.application.OAuthResource;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationFailedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class KakaoOAuthGateway implements OAuthGateway {
    @Value("${oauth2.client.kakao.resource-uri}")
    private String resourceUri;

    private final RestClient restClient;

    @Override
    public OAuthResource authenticate(String accessToken) {
        KakaoResourceResponse resourceResponse = requestResource(accessToken);
        return OAuthResource.of(OAuthType.KAKAO, resourceResponse.id());
    }

    private KakaoResourceResponse requestResource(String accessToken) {
        ResponseEntity<KakaoResourceResponse> resource = restClient
                .get()
                .uri(resourceUri)
                .headers(headers -> headers.setBearerAuth(accessToken))
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new AuthenticationFailedException();
                })
                .toEntity(KakaoResourceResponse.class);
        return resource.getBody();
    }

    @JsonNaming(SnakeCaseStrategy.class)
    private record KakaoResourceResponse(
            String id
    ) {
    }

    @Override
    public OAuthType getType() {
        return OAuthType.KAKAO;
    }
}