package net.likelion.dailytales.writing.api.dto.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import net.likelion.dailytales.writing.application.statistics.MainKeywordDto;

import java.util.List;

@JsonNaming(SnakeCaseStrategy.class)
public record MainKeywordsResponse(
        List<MainKeywordDto> mainKeywords
) {
    public static MainKeywordsResponse from(List<MainKeywordDto> mainKeywords) {
        return new MainKeywordsResponse(mainKeywords);
    }
}
