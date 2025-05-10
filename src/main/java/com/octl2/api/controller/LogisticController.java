package com.octl2.api.controller;

import com.octl2.api.dto.response.LogisticsResponse;
import com.octl2.api.service.LogisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/logistics")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LogisticController {
    private final LogisticService logisticService;

    @GetMapping("/provinces")
    public ResponseEntity<List<LogisticsResponse>> getLogisticsByProvince() {
        List<LogisticsResponse> response = logisticService.getLogisticsByProvince();
        return ResponseEntity.ok(response);
    }

    @GetMapping("/districts")
    public ResponseEntity<List<LogisticsResponse>> getLogisticsByDistrict(@RequestParam Long provinceId) {
        List<LogisticsResponse> response = logisticService.getLogisticsByDistrict(provinceId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/subdistricts")
    public ResponseEntity<List<LogisticsResponse>> getLogisticsBySubdistrict(@RequestParam Long districtId) {
        List<LogisticsResponse> response = logisticService.getLogisticsBySubdistrict(districtId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/export/excel")
    public ResponseEntity<byte[]> exportLogisticsToExcel() {
        try {
            byte[] excelFile = logisticService.exportLogisticsToExcel();
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=logistics_export.xlsx")
                    .contentType(MediaType.parseMediaType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"))
                    .body(excelFile);
        } catch (IOException e) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
