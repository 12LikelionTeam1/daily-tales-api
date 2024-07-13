package net.likelion.dailytales.writing.application.statistics;

import java.time.Month;
import java.time.Year;
import java.util.Map;

public interface WritingsCountSupport {

    Map<Month, Integer> countTotalWritingsPerMonth(String userId, Year year);

    Map<Integer, Integer> countTotalWritingsPerDay(String userId, Year year, Month month);

}
