package net.likelion.dailytales.authentication.infrastructure.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthType;
import net.likelion.dailytales.authentication.application.SocialAccount;

@Entity(name = "`social_account`")
@IdClass(SocialAccountEntityKey.class)
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialAccountEntity {
    @Id
    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private OAuthType provider;

    @Id
    @Column(name = "social_id")
    private String socialId;

    @Column(name = "user_id")
    private String userId;

    public SocialAccount toDomain() {
        return SocialAccount.of(provider, socialId, userId);
    }

    public static SocialAccountEntity from(SocialAccount account) {
        return new SocialAccountEntity(account.type(), account.socialId(), account.userId());
    }
}

