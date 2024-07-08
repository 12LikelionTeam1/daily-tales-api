package net.likelion.dailytales.authentication.infrastructure.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public class APIAuthentication implements Authentication {
    private final String userId;
    private final String accessToken;

    private boolean isAuthenticated = true;

    private APIAuthentication(String userId, String accessToken) {
        this.userId = userId;
        this.accessToken = accessToken;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public String getCredentials() {
        return accessToken;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return userId;
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;
    }

    @Override
    public String getName() {
        return userId;
    }

    public static APIAuthentication of(String userId, String accessToken) {
        return new APIAuthentication(userId, accessToken);
    }
}
