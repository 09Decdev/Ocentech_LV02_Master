package com.octl2.api.mapper;

import com.octl2.api.dto.response.ProvinceResponseDto;
import com.octl2.api.entity.Province;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel ="spring")
public interface ProvinceMapper {
    @Mapping(source = "id",target = "prvId")
    ProvinceResponseDto toDto(Province province);
}
