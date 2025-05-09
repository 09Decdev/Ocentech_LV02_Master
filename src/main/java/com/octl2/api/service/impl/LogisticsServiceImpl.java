package com.octl2.api.service.impl;

import com.octl2.api.config.LogisticConfig;
import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.dto.response.LogisticsResponse;
import com.octl2.api.mapper.LogisticByProvinceMapper;
import com.octl2.api.repository.DistrictLevelLogisticsRepository;
import com.octl2.api.repository.LogisticsRepository;
import com.octl2.api.repository.ProvinceLevelLogisticsRepository;
import com.octl2.api.repository.SubdistrictLevelLogisticsRepository;
import com.octl2.api.service.LogisticService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LogisticsServiceImpl implements LogisticService {
    private final DistrictLevelLogisticsRepository districtRepo;
    private final ProvinceLevelLogisticsRepository provinceRepo;
    private final SubdistrictLevelLogisticsRepository subdistrictRepo;
    private final LogisticsRepository logisticsRepo;
    private final LogisticByProvinceMapper LogisticsByProvinceMapper;
    private final LogisticConfig logisticConfig;

    @Override
    public List<LogisticsResponse> getLogisticsByProvince() {
        int mappingLevel = logisticConfig.getMappingLevel();
        log.info("Current mapping level: {}", mappingLevel);
        List<LogisticsByLocationDto> data;
        switch (mappingLevel) {
            case 1:
                data = provinceRepo.findAllProvinces();
                log.info("Data from repo: {}", data);
                break;
            case 2:
                data = districtRepo.findAllDistricts();
                log.info("Data from repo: {}", data);
                break;
            case 3:
                data = subdistrictRepo.findAllSubdistricts();
                log.info("Data from repo: {}", data);
                break;
            default:
                log.error("Invalid mapping level: {}", mappingLevel);
                throw new IllegalArgumentException("Invalid mapping level");
        }
        return LogisticsByProvinceMapper.mapToLogisticsResponse(data);
    }

    @Override
    public List<LogisticsResponse> getLogisticsByDistrict(Long provinceId) {
        int mappingLevel = logisticConfig.getMappingLevel();
        log.info("Current mapping level: {}", mappingLevel);
        List<LogisticsByLocationDto> data;
        switch (mappingLevel) {
            case 1:
                data = provinceRepo.findDistrictsByProvince(provinceId);
                break;
            case 2:
                data = districtRepo.findSubdistrictsByDistrict(provinceId);
                break;
            case 3:
                data = subdistrictRepo.findSubdistrictsByDistrict(provinceId);
                break;
            default:
                log.info("Current mapping level: {}", mappingLevel);
                throw new IllegalArgumentException("Invalid mapping level");
        }
        return LogisticsByProvinceMapper.mapToLogisticsResponse(data);
    }

    @Override
    public List<LogisticsResponse> getLogisticsBySubdistrict(Long districtId) {
        int mappingLevel = logisticConfig.getMappingLevel();
        log.info("Current mapping level: {}", mappingLevel);
        List<LogisticsByLocationDto> data;
        switch (mappingLevel) {
            case 1:
                data = provinceRepo.findSubdistrictsByProvince(districtId);
                break;
            case 2:
                data = districtRepo.findSubdistrictsByDistrict(districtId);
                break;
            case 3:
                data = subdistrictRepo.findSubdistrictsByDistrict(districtId);
                break;
            default:
                log.info("Current mapping level: {}", mappingLevel);
                throw new IllegalArgumentException("Invalid mapping level");
        }
        return LogisticsByProvinceMapper.mapToLogisticsResponse(data);
    }
}
