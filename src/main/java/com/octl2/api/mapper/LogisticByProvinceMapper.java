package com.octl2.api.mapper;

import com.octl2.api.dto.response.LogisticDto;
import com.octl2.api.dto.response.LogisticsByProvinceDto;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class LogisticByProvinceMapper {
    public static List<LogisticsByProvinceDto> mapToDtoList(List<Object[]> results) {
        List<LogisticsByProvinceDto> dtos = new ArrayList<>();

        for (Object[] result : results) {
            Long provinceId = convertToLong(result[2]);
            LogisticsByProvinceDto dto = findOrCreateDto(dtos, provinceId);
            dto.setProvinceName((String)result[3]);
            // Add logistics for FFM, Lastmile, and Warehouse using a helper method
            dto.addLogistic(createLogisticDto(result[4], result[5], "FFM"));
            dto.addLogistic(createLogisticDto(result[6], result[7], "Lastmile"));
            dto.addLogistic(createLogisticDto(result[8], result[9], "Warehouse"));
        }

        return dtos;
    }

    // Helper method to convert an object to Long
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

    private static LogisticsByProvinceDto findOrCreateDto(List<LogisticsByProvinceDto> dtos, Long provinceId) {
        for (LogisticsByProvinceDto dto : dtos) {
            if (dto.getProvinceId().equals(provinceId)) {
                return dto;
            }
        }
        LogisticsByProvinceDto newDto = new LogisticsByProvinceDto();
        newDto.setProvinceId(provinceId);
        dtos.add(newDto);
        return newDto;
    }
}
