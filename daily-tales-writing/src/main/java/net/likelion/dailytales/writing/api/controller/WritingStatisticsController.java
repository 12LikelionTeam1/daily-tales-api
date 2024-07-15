package net.likelion.dailytales.writing.api.controller;

import lombok.RequiredArgsConstructor;
import net.likelion.dailytales.core.domain.authentication.LoggedInUser;
import net.likelion.dailytales.writing.api.dto.response.MainKeywordsResponse;
import net.likelion.dailytales.writing.api.dto.response.PublishedWritingsResponse;
import net.likelion.dailytales.writing.api.dto.response.TotalWritingsPerDayResponse;
import net.likelion.dailytales.writing.api.dto.response.TotalWritingsPerMonthResponse;
import net.likelion.dailytales.writing.application.statistics.MainKeywordDto;
import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerDayDto;
import net.likelion.dailytales.writing.application.statistics.TotalWritingsPerMonthDto;
import net.likelion.dailytales.writing.application.statistics.WritingStatisticsService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/me/writings/statistics")
public class WritingStatisticsController {
    private final WritingStatisticsService writingStatisticsService;

    @GetMapping("/main-keywords")
    public MainKeywordsResponse getMainKeywords(
            @LoggedInUser String userId,
            @RequestParam(required = false, name = "size", defaultValue = "3") Integer size
    ) {
        List<MainKeywordDto> result = writingStatisticsService.getMainKeywords(userId, size);

        return MainKeywordsResponse.from(result);
    }

    @GetMapping("/total-writings-per-month")
    public TotalWritingsPerMonthResponse getTotalWritingsPerMonth(
            @LoggedInUser String userId,
            @RequestParam(required = false, name = "year") Integer yearValue
    ) {
        Year year = yearValue == null ? Year.now() : Year.of(yearValue);
        TotalWritingsPerMonthDto countingResult = writingStatisticsService
                .getTotalWritingsPerMonth(userId, year);

        return TotalWritingsPerMonthResponse.of(countingResult);
    }

    @GetMapping("/total-writings-per-day")
    public TotalWritingsPerDayResponse getTotalWritingsPerDay(
            @LoggedInUser String userId,
            @RequestParam(required = false, name = "year") Integer yearValue,
            @RequestParam(required = false, name = "month") Integer monthValue
    ) {
        Year year = yearValue == null ? Year.now() : Year.of(yearValue);
        Month month = monthValue == null ? LocalDate.now().getMonth() : Month.of(monthValue);
        TotalWritingsPerDayDto countingResult = writingStatisticsService
                .getTotalWritingsPerDay(userId, year, month);

        return TotalWritingsPerDayResponse.of(countingResult);
    }

    @GetMapping("/published-writings")
    public PublishedWritingsResponse countPublishedWritings(@AuthenticationPrincipal String userId) {
        Long publishedWritings = writingStatisticsService.countPublishedWritings(userId);

        return PublishedWritingsResponse.of(publishedWritings);
    }
}
