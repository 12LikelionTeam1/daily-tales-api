package net.likelion.dailytales.authentication.infrastructure.persistence.repository.jpa;

import net.likelion.dailytales.authentication.infrastructure.persistence.entity.SocialAccountEntityKey;
import net.likelion.dailytales.authentication.infrastructure.persistence.entity.SocialAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserSocialAccountRepository extends JpaRepository<SocialAccountEntity, SocialAccountEntityKey> {
}
