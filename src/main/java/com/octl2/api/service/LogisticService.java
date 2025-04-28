package com.octl2.api.service;

import com.octl2.api.dto.response.LogisticsByProvinceDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LogisticService {
    List<LogisticsByProvinceDto> findAllLogisticsByProvince();
}
