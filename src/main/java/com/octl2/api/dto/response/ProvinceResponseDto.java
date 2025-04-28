package com.octl2.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ProvinceResponseDto {

    private Long prvId;

    private String name;

    private String shortname;

    private String code;

   private String description;
   private List<WarehouseResponseDto>warehouseResponseDtos;
   private List<PartnerResponseDto> partnerResponseDtos;
}
