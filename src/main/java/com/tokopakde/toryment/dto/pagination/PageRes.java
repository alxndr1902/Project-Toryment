package com.tokopakde.toryment.dto.pagination;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResDTO<T> {
    private List<T> data;
    private PageMeta meta;

    public PageResDTO(List<T> data, PageMeta meta) {
        this.data = data;
        this.meta = meta;
    }
}
