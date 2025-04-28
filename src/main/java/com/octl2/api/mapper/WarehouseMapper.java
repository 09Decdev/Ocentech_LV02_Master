package com.octl2.api.mapper;

import com.octl2.api.dto.response.WarehouseResponseDto;
import com.octl2.api.entity.Warehourse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface WarehouseMapper {
    WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);
//    WarehouseResponseDto toDto(Warehourse warehourse);
}
