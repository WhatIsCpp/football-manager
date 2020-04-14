package com.app.demo.util;

import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@NoArgsConstructor
public class ControllerUtils {

    public static Pageable createPageable(int size, int page, String sort) {

        String sortField = (sort != null ? sort : "id");
        Sort sortBy = Sort.by(sortField);

        if (size == 0 && page == 0) {

            Pageable.unpaged();
        }

        return PageRequest.of(page, size, sortBy);
    }
}
