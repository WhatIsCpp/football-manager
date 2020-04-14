package com.app.demo.util;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public final class CalculationUtils {

    private CalculationUtils() {
    }

    public static long calculateYearsBetween(LocalDate dateFrom, LocalDate dateTo) {

        return ChronoUnit.YEARS.between(dateFrom, dateTo);
    }
}
