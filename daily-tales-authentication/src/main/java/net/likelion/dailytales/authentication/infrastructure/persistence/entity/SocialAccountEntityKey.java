package net.likelion.dailytales.authentication.infrastructure.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.likelion.dailytales.authentication.application.OAuthType;

import java.io.Serializable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SocialAccountEntityKey implements Serializable {
    private OAuthType provider = OAuthType.GOOGLE;
    private String socialId = "";
}
