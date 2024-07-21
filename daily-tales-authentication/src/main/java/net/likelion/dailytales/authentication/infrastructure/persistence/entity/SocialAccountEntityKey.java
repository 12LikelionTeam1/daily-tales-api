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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SocialAccountEntityKey that = (SocialAccountEntityKey) o;

        if (provider != that.provider) return false;
        return socialId.equals(that.socialId);
    }

    @Override
    public int hashCode() {
        int result = provider.hashCode();
        result = 31 * result + socialId.hashCode();
        return result;
    }
}
