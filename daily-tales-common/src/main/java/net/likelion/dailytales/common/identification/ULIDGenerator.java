package net.likelion.dailytales.common.identification;

import com.github.f4b6a3.ulid.UlidCreator;
import net.likelion.dailytales.core.global.IdentifierGenerator;
import org.springframework.stereotype.Component;

@Component
public class ULIDGenerator implements IdentifierGenerator {
    @Override
    public String generate() {
        return UlidCreator.getMonotonicUlid().toString();
    }
}
