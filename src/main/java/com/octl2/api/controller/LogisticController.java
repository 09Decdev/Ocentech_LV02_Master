package com.octl2.api.controller;

import com.octl2.api.dto.response.LogisticsByProvinceDto;
import com.octl2.api.service.LogisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/logistics")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LogisticController {
    private final LogisticService logisticService;
    @GetMapping("/logistics-by-province")
    public List<LogisticsByProvinceDto> getAllLogisticsByProvince() {
        return logisticService.findAllLogisticsByProvince();
    }
}
