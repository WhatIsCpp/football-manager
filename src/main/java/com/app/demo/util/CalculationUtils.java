package com.app.demo.util;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NoArgsConstructor
public class CalculationUtils {

    public static long calculateYearsBetween(LocalDate dateFrom, LocalDate dateTo) {

        return ChronoUnit.YEARS.between(dateFrom, dateTo);
    }
}
