package com.octl2.api.service;

import com.octl2.api.dto.response.LogisticsResponse;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public interface LogisticService {
    List<LogisticsResponse> getLogisticsByProvince();

    List<LogisticsResponse> getLogisticsByDistrict(Long provinceId);

    List<LogisticsResponse> getLogisticsBySubdistrict(Long districtId);

    byte[] exportLogisticsToExcel() throws IOException;
}
