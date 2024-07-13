package net.likelion.dailytales.writing.api.dto.response;

import net.likelion.dailytales.writing.application.statistics.MainKeywordDto;

import java.util.List;

public record MainKeywordsResponse(
        List<MainKeywordDto> mainKeywords
) {
    public static MainKeywordsResponse from(List<MainKeywordDto> mainKeywords) {
        return new MainKeywordsResponse(mainKeywords);
    }
}
