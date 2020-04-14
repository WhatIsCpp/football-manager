package com.app.demo.util;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public final class ControllerUtils {

    private ControllerUtils() {
    }

    public static Pageable createPageable(int size, int page, String sort) {

        String sortField = (sort != null ? sort : "id");
        Sort sortBy = Sort.by(sortField);

        return PageRequest.of(page, size, sortBy);
    }
}
