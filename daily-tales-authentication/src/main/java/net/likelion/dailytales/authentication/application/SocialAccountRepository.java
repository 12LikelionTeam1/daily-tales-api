package net.likelion.dailytales.authentication.application;

import jakarta.annotation.Nullable;

public interface SocialAccountRepository {

    void save(SocialAccount socialAccount);

    @Nullable
    SocialAccount findByTypeAndUserId(OAuthType type, String userId);

    void deleteByTypeAndUserId(OAuthType type, String userId);

}