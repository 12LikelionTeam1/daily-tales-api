package net.likelion.dailytales.writing.application.statistics;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WritingStatisticsService {
    private final KeywordStatisticsSupport keywordStatisticsSupport;
    private final TotalWritingsCountSupport totalWritingsCountSupport;

    public List<MainKeywordDto> getMainKeywords(String userId, Integer size) {
        return keywordStatisticsSupport.getMainKeywords(userId, size);
    }

    public TotalWritingsPerMonthDto getTotalWritingsPerMonth(String userId, Year year) {
        Map<Month, Integer> countingResult = totalWritingsCountSupport.getTotalWritingsPerMonth(userId, year);
        for (Month month : Month.values()) {
            countingResult.putIfAbsent(month, 0);
        }
        int totalWritingsOfYear = countingResult
                .values()
                .stream()
                .mapToInt(Integer::intValue)
                .sum();
        return new TotalWritingsPerMonthDto(totalWritingsOfYear, countingResult);
    }

    public TotalWritingsPerDayDto getTotalWritingsPerDay(String userId, Year year, Month month) {
        return new TotalWritingsPerDayDto(
                totalWritingsCountSupport.getTotalWritingsPerDay(userId, year, month)
        );
    }
}
