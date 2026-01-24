package com.tokopakde.toryment.pagination;

import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;

public final class PageMapper {
    private PageMapper() {} // prevent instantiation

    public static <T, R> PageResponse<R> toPageResponse(Page<T> page, Function<T, R> mapper) {
        List<R> data = page.getContent()
                .stream()
                .map(mapper)
                .toList();

        PageMeta meta = new PageMeta(
                page.getNumber(),
                page.getSize(),
                page.getTotalElements(),
                page.getTotalPages(),
                page.hasNext(),
                page.hasPrevious()
        );

        return new PageResponse<>(data, meta);
    }
}
