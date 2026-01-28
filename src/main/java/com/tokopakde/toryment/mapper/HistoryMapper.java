package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.history.HistoryResDTO;
import com.tokopakde.toryment.model.company.History;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface HistoryMapper {
    @Mapping(target = "statusCode", source = "historyStatus.code")
    @Mapping(target = "dateTime", source = "createdAt")
    HistoryResDTO mapToDto(History history);
}
