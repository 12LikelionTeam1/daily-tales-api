package net.likelion.dailytales.authentication.application;

public interface  OAuthGateway {

    OAuthResource authenticate(String token);

    OAuthType getType();

}
