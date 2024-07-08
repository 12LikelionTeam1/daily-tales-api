package net.likelion.dailytales.authentication.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.likelion.dailytales.authentication.infrastructure.JwtTokenProvider;
import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenType;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationFailedException;
import net.likelion.dailytales.core.global.exception.APIException;
import net.likelion.dailytales.core.global.exception.ErrorCode;
import net.likelion.dailytales.core.global.exception.ErrorResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomJwtFilter extends OncePerRequestFilter {
    @Value("${app.headers.auth-token-header}")
    private String authTokenHeader;

    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(
            @Nonnull HttpServletRequest request,
            @Nonnull HttpServletResponse response,
            @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {
        String token = request.getHeader(authTokenHeader);
        if (token == null) {
            filterChain.doFilter(request, response);
            return;
        }
        try {
            Token accessToken = jwtTokenProvider.makeTokenFrom(token);
            if (accessToken.type() != TokenType.ACCESS) {
                throw new AuthenticationFailedException();
            }
            String userId = jwtTokenProvider.extractUserId(accessToken);
            APIAuthentication authentication = APIAuthentication.of(userId, token);
            SecurityContextHolder.getContext().setAuthentication(authentication);

            filterChain.doFilter(request, response);
        } catch (ExpiredJwtException exception) {
            writeErrorResponse(response, HttpStatus.UNAUTHORIZED, ErrorCode.AUTHENTICATION_EXPIRED);
        } catch (JwtException exception) {
            writeErrorResponse(response, HttpStatus.UNAUTHORIZED, ErrorCode.AUTHENTICATION_FAILED);
        } catch (APIException exception) {
            writeErrorResponse(response, HttpStatus.BAD_REQUEST, exception.errorCode());
        }
    }

    private void writeErrorResponse(
            HttpServletResponse response,
            HttpStatus status,
            ErrorCode errorCode
    ) throws IOException {
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");
        response.setStatus(status.value());
        response.getWriter().write(
                objectMapper.writeValueAsString(ErrorResponse.of(errorCode))
        );
    }
}
