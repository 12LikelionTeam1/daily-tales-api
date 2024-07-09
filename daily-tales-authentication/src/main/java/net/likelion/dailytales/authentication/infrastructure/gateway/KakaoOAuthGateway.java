package net.likelion.dailytales.authentication.infrastructure.gateway;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthGateway;
import net.likelion.dailytales.authentication.application.OAuthResource;
import net.likelion.dailytales.authentication.application.OAuthType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KakaoOAuthGateway implements OAuthGateway {
    @Value("${oauth2.client.kakao.resource-uri}")
    private String resourceUri;

    @Override
    public OAuthResource authenticate(String accessToken) {
        return null;
    }

    @Override
    public OAuthType getType() {
        return OAuthType.KAKAO;
    }
}