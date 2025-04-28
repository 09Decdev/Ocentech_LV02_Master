package com.octl2.api.service.impl;

import com.octl2.api.dto.response.LogisticsByProvinceDto;
import com.octl2.api.mapper.LogisticByProvinceMapper;
import com.octl2.api.repository.LogisticsRepository;
import com.octl2.api.service.LogisticService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class LogisticsServiceImpl implements LogisticService {
    private final LogisticsRepository logisticsRepo;
    private final LogisticByProvinceMapper LogisticsByProvinceMapper;
    @Override
    public List<LogisticsByProvinceDto> findAllLogisticsByProvince() {
        List<Object[]> results = logisticsRepo.findAllLogisticsByProvince();
        return LogisticsByProvinceMapper.mapToDtoList(results);
    }
}
