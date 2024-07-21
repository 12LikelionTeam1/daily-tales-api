package net.likelion.dailytales.authentication.infrastructure;

import java.util.List;

public record OIDCPublicKeysResponse(
        List<OIDCPublicKey> keys
) {
}
