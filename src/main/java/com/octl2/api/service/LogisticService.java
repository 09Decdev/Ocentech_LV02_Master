package com.octl2.api.service;

import com.octl2.api.dto.response.LogisticsByDistrictDto;
import com.octl2.api.dto.response.LogisticsByProvinceDto;
import com.octl2.api.dto.response.LogisticsResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogisticService {
    List<LogisticsResponse> getLogisticsByProvince();
    List<LogisticsResponse> getLogisticsByDistrict(Long provinceId);
    List<LogisticsResponse> getLogisticsBySubdistrict(Long districtId);
}
