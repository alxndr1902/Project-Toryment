package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import com.tokopakde.toryment.model.company.History;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    HistoryResDTO toDto(History history);
}
