package net.likelion.dailytales.authentication.infrastructure.security;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import net.likelion.dailytales.authentication.infrastructure.security.JwtTokenProvider;
import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenType;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class JwtTokenProviderTest {

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Test
    public void generateToken() {
        // Given
        String userId = "abcdefg";
        TokenType type = TokenType.ACCESS;

        // When
        Token token = tokenProvider.generateToken(type, userId);


        System.out.println("token test : " +token.value());
        // Then
        assertNotNull(token);
        assertEquals(TokenType.ACCESS, token.type());
        assertNotNull(token.value());
    }
}
