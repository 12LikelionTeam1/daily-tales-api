package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.writing.api.dto.response.MainKeywordsResponse;
import net.likelion.dailytales.writing.application.statistics.MainKeywordDto;
import net.likelion.dailytales.writing.application.statistics.WritingStatisticsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me/writing/statistics")
public class WritingStatisticsController {
    private final WritingStatisticsService writingStatisticsService;

    @GetMapping("/main-keywords")
    public MainKeywordsResponse getMainKeywords(
            @AuthenticationPrincipal String userId,
            @RequestParam(name = "size", defaultValue = "3") Integer size
    ) {
        List<MainKeywordDto> result = writingStatisticsService.getMainKeywords(userId, size);

        return MainKeywordsResponse.from(result);
    }
}
