package com.octl2.api.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticsByProvinceDto {
    private Long provinceId;
    private String provinceName;
    List<LogisticDto>logistics;
    public void addLogistic(LogisticDto logistic) {
        if (this.logistics == null) {
            this.logistics = new ArrayList<>();
        }
        this.logistics.add(logistic);
    }
}
