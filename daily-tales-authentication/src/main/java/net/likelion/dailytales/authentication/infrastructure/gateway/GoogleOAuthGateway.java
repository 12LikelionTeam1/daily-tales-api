package net.likelion.dailytales.authentication.infrastructure.gateway;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.likelion.dailytales.authentication.application.OAuthGateway;
import net.likelion.dailytales.authentication.application.OAuthResource;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.authentication.infrastructure.GoogleOIDCClient;
import net.likelion.dailytales.authentication.infrastructure.JwtOIDCSupport;
import net.likelion.dailytales.authentication.infrastructure.OIDCDecodePayload;
import net.likelion.dailytales.authentication.infrastructure.OIDCPublicKeysResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GoogleOAuthGateway implements OAuthGateway {
    @Value("${oauth2.client.google.issuer}")
    private String issuer;

    @Value("${oauth2.client.google.client-id}")
    private String clientId;

    private final GoogleOIDCClient client;
    private final JwtOIDCSupport oidcSupport;

    @Override
    public OAuthResource authenticate(String token) {
        OIDCPublicKeysResponse keys = client.getGoogleOIDCPublicKeys();
        OIDCDecodePayload payload = oidcSupport.getPayloadFromIdToken(
                token,
                issuer,
                clientId,
                keys
        );
        return OAuthResource.of(OAuthType.GOOGLE, payload.getSub());
    }

    @Override
    public OAuthType getType() {
        return OAuthType.GOOGLE;
    }
}