package net.likelion.dailytales.writing.application.statistics;

import java.time.Month;
import java.time.Year;
import java.util.List;
import java.util.Map;

public interface TotalWritingsCountSupport {

    Map<Month, Integer> getTotalWritingsPerMonth(String userId, Year year);

    Map<Integer, Integer> getTotalWritingsPerDay(String userId, Year year, Month month);

}
