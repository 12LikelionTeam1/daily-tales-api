package net.likelion.dailytales.authentication.infrastructure.security;

import io.jsonwebtoken.Jwts;
import jakarta.annotation.PostConstruct;
import net.likelion.dailytales.authentication.application.TokenProvider;
import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Date;
import java.util.Map;

@Component
public class JwtTokenProvider implements TokenProvider {
    @Value("${token.secret}")
    private String secret;

    @Value("${token.expiration.access}")
    private Long accessTokenExpiration;

    @Value("${token.expiration.refresh}")
    private Long refreshTokenExpiration;

    private SecretKey signKey;

    @PostConstruct
    public void init() {
        signKey = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
    }

    @Override
    public Token generateToken(TokenType type, String userId) {
        if (type == TokenType.ACCESS) {
            return generateAccessToken(userId);
        } else if (type == TokenType.REFRESH) {
            return generateRefreshToken(userId);
        }
        throw new IllegalArgumentException("Unsupported token type: " + type);
    }

    @Override
    public String extractUserId(Token token) {
        return Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(token.value())
                .getPayload()
                .get("userId", String.class);
    }

    @Override
    public Token makeTokenFrom(String value) {
        TokenType type = TokenType.valueOf(
                Jwts.parser()
                        .verifyWith(signKey)
                        .build()
                        .parseSignedClaims(value)
                        .getHeader()
                        .get("cat")
                        .toString()
        );
        return new Token(value, type);
    }

    private Token generateAccessToken(String userId) {
        String tokenValue = Jwts
                .builder()
                .header().add(buildHeader(TokenType.ACCESS)).and()
                .claims(buildPayload(userId))
                .expiration(buildAccessTokenExpiration())
                .signWith(signKey)
                .compact();
        return new Token(tokenValue, TokenType.ACCESS);
    }

    private Token generateRefreshToken(String userId) {
        String tokenValue = Jwts
                .builder()
                .header().add(buildHeader(TokenType.REFRESH)).and()
                .claims(buildPayload(userId))
                .expiration(buildRefreshTokenExpiration())
                .signWith(signKey)
                .compact();
        return new Token(tokenValue, TokenType.REFRESH);
    }

    private Map<String, Object> buildHeader(TokenType type) {
        return Map.of(
                "typ", "JWT",
                "cat", type.name(),
                "alg", "HS256",
                "regDate", System.currentTimeMillis()
        );
    }

    private Map<String, Object> buildPayload(String userId) {
        return Map.of("userId", userId);
    }

    private Date buildAccessTokenExpiration() {
        return new Date(System.currentTimeMillis() + accessTokenExpiration * 1000);
    }

    private Date buildRefreshTokenExpiration() {
        return new Date(System.currentTimeMillis() + refreshTokenExpiration * 1000);
    }
}
