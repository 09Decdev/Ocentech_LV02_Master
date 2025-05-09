package com.octl2.api.mapper;

import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.dto.response.LogisticDto;
import com.octl2.api.dto.response.LogisticsByProvinceDto;
import com.octl2.api.dto.response.LogisticsResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


@Component
public class LogisticByProvinceMapper {
    public static List<LogisticsByProvinceDto> mapToDtoList(List<Object[]> results) {
        List<LogisticsByProvinceDto> dtos = new ArrayList<>();

        for (Object[] result : results) {
            Long provinceId = convertToLong(result[2]);
            String provinceName = (String) result[3];
            LogisticsByProvinceDto dto = findOrCreateDto(dtos, provinceId, provinceName);

            dto.addLogistic(createLogisticDto(result[4], result[5], "FFM"));
            dto.addLogistic(createLogisticDto(result[6], result[7], "Lastmile"));
            dto.addLogistic(createLogisticDto(result[8], result[9], "Warehouse"));
        }

        return dtos;
    }


    private static Long convertToLong(Object obj) {
        if (obj == null) {
            return null;
        }
        if (obj instanceof Integer) {
            return ((Integer) obj).longValue();
        }
        return (Long) obj;
    }

    // Helper method to create LogisticDto
    private static LogisticDto createLogisticDto(Object idObj, Object nameObj, String type) {
        LogisticDto logisticDto = new LogisticDto();
        Long id = convertToLong(idObj);
        logisticDto.setId(id);
        logisticDto.setName((String) nameObj);
        logisticDto.setType(type);
        return logisticDto;
    }

    private static LogisticsByProvinceDto findOrCreateDto(List<LogisticsByProvinceDto> dtos, Long provinceId, String name) {
        for (LogisticsByProvinceDto dto : dtos) {
            if (dto.getProvinceId().equals(provinceId)) {
                return dto;
            }
        }
        LogisticsByProvinceDto newDto = new LogisticsByProvinceDto();
        newDto.setProvinceId(provinceId);
        newDto.setProvinceName(name);
        dtos.add(newDto);
        return newDto;
    }


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
