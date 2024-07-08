package net.likelion.dailytales.authentication.infrastructure.gateway;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthGateway;
import net.likelion.dailytales.authentication.application.OAuthGatewayFactory;
import net.likelion.dailytales.authentication.application.OAuthType;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthGatewayFactoryImpl implements OAuthGatewayFactory {
    private final ObjectProvider<OAuthGateway> oAuthGateways;

    @Override
    public OAuthGateway of(OAuthType type) {
        return oAuthGateways.stream()
                .filter(gateway -> gateway.getType() == type)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Unsupported OAuth type: " + type));
    }
}