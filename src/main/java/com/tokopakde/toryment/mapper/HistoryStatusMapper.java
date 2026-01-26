package com.tokopakde.toryment.mapper;

import com.tokopakde.toryment.dto.history.HistoryStatusResDTO;
import com.tokopakde.toryment.model.company.HistoryStatus;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface HistoryStatusMapper {
    HistoryStatusResDTO toDto(HistoryStatus historyStatus);
}
