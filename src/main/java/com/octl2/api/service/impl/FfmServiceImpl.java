package com.octl2.api.service.impl;

import com.octl2.api.dto.response.FfmDetailResponseDto;
import com.octl2.api.entity.Partner;
import com.octl2.api.entity.Warehourse;
import com.octl2.api.mapper.FfmDetailMapper;
import com.octl2.api.repository.CfMappingFfmLmRepository;
import com.octl2.api.repository.PartnerRepository;
import com.octl2.api.repository.WarehouseRepository;
import com.octl2.api.service.FfmService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class FfmServiceImpl implements FfmService {
    private final PartnerRepository partnerRepo;
    private final CfMappingFfmLmRepository cfMappingFfmLmRepo;
    private final WarehouseRepository warehouseRepo;
    private final FfmDetailMapper ffmDetailMapper;

    @Override
    public FfmDetailResponseDto getFfmDetail(Long ffmId) {
        return null;
    }

    @Override
    public List<FfmDetailResponseDto> getAllFfmDetails() {
        List<Partner> ffms = partnerRepo.findAll()
                .stream()
                .filter(partner -> Objects.equals(partner.getPartnerType(), 122L))
                .collect(Collectors.toList());
        log.info("ffms: {}", ffms);
        Map<Long, List<Partner>> lmMap = new HashMap<>();
        for (Partner ffm : ffms) {
            List<Partner> lms = cfMappingFfmLmRepo.findLmsByFfmId(ffm.getPartnerId());
            lmMap.put(ffm.getPartnerId(), lms);
        }
        log.info("lmMap: {}", lmMap);

        Map<Long, List<Warehourse>> whMap = new HashMap<>();
        for (Partner ffm : ffms) {
            List<Warehourse> whs = warehouseRepo.findWarehourseByFfmId(ffm.getPartnerId());
            whMap.put(ffm.getPartnerId(), whs);
        }
        log.info("whMap: {}", whMap);
        return ffmDetailMapper.toDtoList(ffms, lmMap, whMap);
    }
}