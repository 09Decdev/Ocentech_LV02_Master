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
public class LogisticsResponse {
    private Long locationId;
    private String locationName;
    private String levelType;
    private List<LogisticDto> logistics;
}
