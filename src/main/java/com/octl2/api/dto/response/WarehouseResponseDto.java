package com.octl2.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class WarehouseResponseDto {
    private Integer warehouseId;
    private String warehouseName;
    private String warehouseShortname;
    private String contactName;
    private String contactPhone;
    private String address;
    private String fullAddress;
    private String email;
    private String provinceName;
    private String districtName;
    private String wardsName;
    private Double latitude;
    private Double longitude;
    private Boolean isMain;
    private Boolean isActive;
    private String whCodeInPartner;
    private LocalDateTime createDate;
    private LocalDateTime modifyDate;
}
