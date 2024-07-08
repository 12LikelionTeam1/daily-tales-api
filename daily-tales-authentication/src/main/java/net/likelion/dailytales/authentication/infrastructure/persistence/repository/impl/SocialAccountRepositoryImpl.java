package net.likelion.dailytales.authentication.infrastructure.persistence.repository.impl;

import jakarta.annotation.Nullable;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.authentication.application.SocialAccount;
import net.likelion.dailytales.authentication.application.SocialAccountRepository;
import net.likelion.dailytales.authentication.infrastructure.persistence.entity.SocialAccountEntity;
import net.likelion.dailytales.authentication.infrastructure.persistence.entity.SocialAccountEntityKey;
import net.likelion.dailytales.authentication.infrastructure.persistence.repository.jpa.JpaUserSocialAccountRepository;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class SocialAccountRepositoryImpl implements SocialAccountRepository {
    private final JpaUserSocialAccountRepository jpaRepository;

    @Override
    public void save(SocialAccount socialAccount) {
        jpaRepository.save(SocialAccountEntity.from(socialAccount));
    }

    @Nullable
    @Override
    public SocialAccount findByTypeAndUserId(OAuthType type, String userId) {
        return jpaRepository
                .findById(keyOf(type, userId))
                .map(SocialAccountEntity::toDomain)
                .orElse(null);
    }

    @Override
    public void deleteByTypeAndUserId(OAuthType type, String userId) {
        jpaRepository.deleteById(keyOf(type, userId));
    }

    private static SocialAccountEntityKey keyOf(OAuthType type, String userId) {
        return new SocialAccountEntityKey(type, userId);
    }
}