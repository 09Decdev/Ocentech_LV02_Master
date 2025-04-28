package com.octl2.api.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
public class PartnerResponseDto {
    private Integer partnerId;
    private String name;
    private String shortname;
    private String contact;
    private String phone;
    private String email;
    private String address;
    private Integer partnerType;
    private Integer type;
    private Integer skillLevel;
    private LocalDateTime modifyDate;
}
