package com.octl2.api.mapper;

import com.octl2.api.dto.response.FfmDetailResponseDto;
import com.octl2.api.dto.response.LmResponse;
import com.octl2.api.dto.response.WarehouseResponse;
import com.octl2.api.entity.Partner;
import com.octl2.api.entity.Warehourse;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Component
public class FfmDetailMapper {
    public FfmDetailResponseDto toDto(Partner ffm, List<Partner> lms, List<Warehourse> warehouses) {
        List<LmResponse> lmResponses = lms.stream()
                .map(lm -> new LmResponse(lm.getPartnerId(), lm.getName(), lm.getShortname()))
                .collect(Collectors.toList());

        List<WarehouseResponse> whResponses = warehouses.stream()
                .map(wh -> new WarehouseResponse(wh.getWarehouseId(),
                        wh.getWarehouseName(),
                        wh.getWarehouseShortname()))
                .collect(Collectors.toList());

        return new FfmDetailResponseDto(
                ffm.getPartnerId(),
                ffm.getName(),
                ffm.getShortname(),
                lmResponses,
                whResponses
        );
    }

    // Hàm map list FFM
    public List<FfmDetailResponseDto> toDtoList(List<Partner> ffms,
                                             Map<Long, List<Partner>> lmMap,
                                             Map<Long, List<Warehourse>> whMap) {
        return ffms.stream()
                .map(ffm -> toDto(
                        ffm,
                        lmMap.getOrDefault(ffm.getPartnerId(), Collections.emptyList()),
                        whMap.getOrDefault(ffm.getPartnerId(), Collections.emptyList())
                ))
                .collect(Collectors.toList());
    }
}
