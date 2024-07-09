package net.likelion.dailytales.authentication.application;

public interface  OAuthGateway {

    OAuthResource authenticate(String accessToken);

    OAuthType getType();

}
