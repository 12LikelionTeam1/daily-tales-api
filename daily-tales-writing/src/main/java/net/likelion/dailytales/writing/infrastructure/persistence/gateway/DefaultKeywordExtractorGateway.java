package net.likelion.dailytales.writing.infrastructure.persistence.gateway;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.Token;
import net.likelion.dailytales.core.domain.authentication.TokenProvider;
import net.likelion.dailytales.core.domain.authentication.TokenType;
import net.likelion.dailytales.core.domain.writing.exception.WritingNotRegisterException;
import net.likelion.dailytales.writing.application.KeywordExtractorGateway;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DefaultKeywordExtractorGateway implements KeywordExtractorGateway {
    @Value("${app.keyword-extractor.uri}")
    private String keywordExtractorUri;

    private final RestClient restClient;
    private final TokenProvider tokenProvider;

    @Override
    public List<String> extractKeywords(String writing) {
        Token accessToken = tokenProvider.generateToken(TokenType.ACCESS, "keyword-extractor");
        KeywordExtractorResponse result = restClient
                .get()
                .uri(keywordExtractorUri + "?writing=" + writing)
                .headers(
                        headers -> headers.set("Authorization", accessToken.value())
                )
                .retrieve()
                .onStatus(HttpStatusCode::isError, (request, response) -> {
                    throw new WritingNotRegisterException();
                })
                .toEntity(KeywordExtractorResponse.class)
                .getBody();
        return result == null ? List.of() : result.keywords();
    }

    private record KeywordExtractorResponse(
            List<String> keywords
    ) {
    }
}
