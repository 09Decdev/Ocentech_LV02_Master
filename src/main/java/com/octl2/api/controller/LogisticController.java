package com.octl2.api.controller;

import com.octl2.api.dto.request.UpdateDefaultDeliveryRequest;
import com.octl2.api.dto.response.LogisticsResponse;
import com.octl2.api.service.LogisticService;
import com.octl2.api.service.impl.LocationLevelServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/logistics")
@RequiredArgsConstructor
@Validated
@Slf4j
public class LogisticController {
    private final LogisticService logisticService;
    private final LocationLevelServiceImpl locationLevelService;

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

    @PostMapping("/update")
    public ResponseEntity<Object> updateDefaultDelivery(@RequestBody UpdateDefaultDeliveryRequest request){
        try {
            locationLevelService.updateDefaultDelivery(
                    request.getLocationId(),
                    request.getFfmId(),
                    request.getLmId(),
                    request.getWhId()
            );
            return ResponseEntity.ok().build();
        }catch (IllegalArgumentException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
    
    
}
