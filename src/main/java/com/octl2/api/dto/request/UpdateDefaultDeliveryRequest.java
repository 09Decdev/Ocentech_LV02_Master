package com.octl2.api.dto.request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class UpdateDefaultDeliveryRequest {
    private Long locationId;
    private int locationLevel;
    private int levelMapping;
    private Long ffmId;
    private Long lmId;
    private Long whId;
}
