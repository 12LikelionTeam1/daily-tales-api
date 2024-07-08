package net.likelion.dailytales.authentication.infrastructure.persistence.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthType;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialAccountEntityKey implements Serializable {
    private OAuthType provider = OAuthType.GOOGLE;
    private String socialId = "";
}
