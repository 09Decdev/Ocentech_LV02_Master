package com.octl2.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FfmDetailResponseDto {
    private Long ffmId;
    private String ffmName;
    private String ffmShortname;
    private List<LmResponse> linkedLms;
    private List<WarehouseResponse> warehouses;
} 