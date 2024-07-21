package net.likelion.dailytales.authentication.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationExpiredException;
import net.likelion.dailytales.core.domain.authentication.exception.AuthenticationFailedException;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;

@Component
@RequiredArgsConstructor
public class JwtOIDCSupport {
    private final ObjectMapper objectMapper;

    public OIDCDecodePayload getPayloadFromIdToken(
            String token,
            String iss,
            String aud,
            OIDCPublicKeysResponse oidcPublicKeysResponse
    ) {
        String kid = getKidFromUnsignedTokenHeader(token, iss, aud);
        OIDCPublicKey oidcPublicKeyDto = oidcPublicKeysResponse
                .keys()
                .stream()
                .filter(o -> o.getKid().equals(kid))
                .findFirst()
                .orElseThrow();
        return getOIDCTokenBody(token, oidcPublicKeyDto.getN(), oidcPublicKeyDto.getE());
    }

    private String getKidFromUnsignedTokenHeader(String token, String iss, String aud) {
        String[] splitToken = token.split("\\.");
        if (splitToken.length != 3) throw new AuthenticationFailedException();
        String headerJson = new String(Base64.getUrlDecoder().decode(splitToken[0]));
        try {
            return objectMapper.readValue(headerJson, JwtHeader.class).kid;
        } catch (Exception exception) {
            throw new AuthenticationFailedException();
        }
    }

    private record JwtHeader(String alg, String kid, String typ) {}

    private Jws<Claims> getOIDCTokenJws(String token, String modulus, String exponent) {
        try {
            return Jwts.parser()
                    .verifyWith(getRSAPublicKey(modulus, exponent))
                    .build()
                    .parseSignedClaims(token);
        } catch (ExpiredJwtException e) {
            throw new AuthenticationExpiredException();
        } catch (Exception e) {
            throw new AuthenticationFailedException();
        }
    }

    private OIDCDecodePayload getOIDCTokenBody(String token, String modulus, String exponent) {
        Claims body = getOIDCTokenJws(token, modulus, exponent).getPayload();
        return new OIDCDecodePayload(
                body.getIssuer(),
                body.getAudience().stream().findFirst().orElseThrow(AuthenticationFailedException::new),
                body.getSubject(),
                body.get("email", String.class));
    }

    private PublicKey getRSAPublicKey(String modulus, String exponent) throws NoSuchAlgorithmException, InvalidKeySpecException {
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        byte[] decodeN = Base64.getUrlDecoder().decode(modulus);
        byte[] decodeE = Base64.getUrlDecoder().decode(exponent);
        BigInteger n = new BigInteger(1, decodeN);
        BigInteger e = new BigInteger(1, decodeE);

        RSAPublicKeySpec keySpec = new RSAPublicKeySpec(n, e);
        return keyFactory.generatePublic(keySpec);
    }
}
