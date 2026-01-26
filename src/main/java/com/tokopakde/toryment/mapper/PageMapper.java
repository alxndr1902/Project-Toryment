package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.pagination.PageMeta;
import com.tokopakde.toryment.dto.pagination.PageResDTO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.function.Function;

@Component
public class PageMapper {
    public <T, R> PageResDTO<R> toPageResponse(Page<T> page, Function<T, R> mapper) {
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

        return new PageResDTO<>(data, meta);
    }
}
