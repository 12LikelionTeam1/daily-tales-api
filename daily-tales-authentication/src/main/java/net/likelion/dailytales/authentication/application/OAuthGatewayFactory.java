package net.likelion.dailytales.authentication.application;

public interface OAuthGatewayFactory {

    OAuthGateway of(OAuthType type);

}