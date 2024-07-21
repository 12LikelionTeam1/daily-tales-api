package net.likelion.dailytales.authentication.infrastructure;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationFailedException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class GoogleOIDCClient {
    private final RestClient restClient;

    @Cacheable(value = "google-oidc", cacheManager = "oidcCacheManager")
    public OIDCPublicKeysResponse getGoogleOIDCPublicKeys() {
        return restClient
                .get()
                .uri("https://www.googleapis.com/oauth2/v3/certs")
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new AuthenticationFailedException();
                })
                .body(OIDCPublicKeysResponse.class);
    }
}
