package com.octl2.api.mapper;

import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.dto.response.LogisticDto;
import com.octl2.api.dto.response.LogisticsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
public class LogisticByProvinceMapper {

    public List<LogisticsResponse> mapToLogisticsResponse(List<LogisticsByLocationDto> projections) {
        Map<Long, LogisticsResponse> groupedMap = new LinkedHashMap<>();

        for (LogisticsByLocationDto dto : projections) {
            Long locationId = dto.getLocationId();

            groupedMap.computeIfAbsent(locationId, id -> {
                LogisticsResponse response = new LogisticsResponse();
                response.setLocationId(id);
                response.setLocationName(dto.getLocationName());
                response.setLevelType(dto.getLevelType());
                response.setLogistics(new ArrayList<>());
                return response;
            });

            // Lấy danh sách logistics và thêm vào
            List<LogisticDto> logistics = groupedMap.get(locationId).getLogistics();

            addLogisticIfPresent(logistics, dto.getFfmId(), dto.getFfmName(), "FFM");
            addLogisticIfPresent(logistics, dto.getLmId(), dto.getLmName(), "LM");
            addLogisticIfPresent(logistics, dto.getWhId(), dto.getWhName(), "WH");
        }

        return new ArrayList<>(groupedMap.values());
    }

    private void addLogisticIfPresent(List<LogisticDto> logistics, Long id, String name, String type) {
        if (id != null) {
            logistics.add(new LogisticDto(name, id, type));
        }
    }


}
