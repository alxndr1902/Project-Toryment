package com.tokopakde.toryment.pagination;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResponse<T> {
    private List<T> data;
    private PageMeta meta;

    public PageResponse(List<T> data, PageMeta meta) {
        this.data = data;
        this.meta = meta;
    }
}
