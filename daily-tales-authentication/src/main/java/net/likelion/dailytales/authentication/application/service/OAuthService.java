package net.likelion.dailytales.authentication.application.service;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.application.*;
import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenProvider;
import net.likelion.dailytales.core.domain.authentication.TokenType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OAuthService {
    private final OAuthGatewayFactory oAuthGatewayFactory;
    private final SocialAccountRepository socialAccountRepository;
    private final SocialUserCreationSupport socialUserCreationSupport;
    private final TokenProvider tokenProvider;

    public OAuthResult authenticate(OAuthTokenDto request) {
        OAuthGateway gateway = oAuthGatewayFactory.of(request.type());
        OAuthResource socialUser = gateway.authenticate(request.code());
        String userId = userIdOf(socialUser);

        Token accessToken = tokenProvider.generateToken(TokenType.ACCESS, userId);
        Token refreshToken = tokenProvider.generateToken(TokenType.REFRESH, userId);

        return new OAuthResult(accessToken, refreshToken);
    }

    private String userIdOf(OAuthResource resource) {
        SocialAccount account = socialAccountRepository.findByTypeAndUserId(resource.type(), resource.socialId());
        if (account == null) {
            return socialUserCreationSupport.create(resource.type(), resource.socialId());
        }
        return account.userId();
    }
}