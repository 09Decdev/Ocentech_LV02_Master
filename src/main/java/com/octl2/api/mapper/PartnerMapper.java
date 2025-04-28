package com.octl2.api.mapper;

import com.octl2.api.dto.response.PartnerResponseDto;
import com.octl2.api.entity.Partner;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PartnerMapper {
    PartnerMapper INSTANCE = Mappers.getMapper(PartnerMapper.class);
    PartnerResponseDto toDto(Partner partner);
}
