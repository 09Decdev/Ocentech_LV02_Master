package com.octl2.api.service.impl;

import com.octl2.api.config.LogisticConfig;
import com.octl2.api.consts.MappingLevel;
import com.octl2.api.dto.LogisticExport;
import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.dto.response.LogisticsResponse;
import com.octl2.api.mapper.LogisticByProvinceMapper;
import com.octl2.api.repository.DistrictLevelLogisticsRepository;
import com.octl2.api.repository.ProvinceLevelLogisticsRepository;
import com.octl2.api.repository.SubdistrictLevelLogisticsRepository;
import com.octl2.api.service.LogisticService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.var;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class LogisticsServiceImpl implements LogisticService {
    private final DistrictLevelLogisticsRepository districtRepo;
    private final ProvinceLevelLogisticsRepository provinceRepo;
    private final SubdistrictLevelLogisticsRepository subdistrictRepo;
    private final LogisticByProvinceMapper provinceMapper;
    private final LogisticConfig logisticConfig;

    @Override
    public List<LogisticsResponse> getLogisticsByProvince() {
        int mappingLevel = logisticConfig.getMappingLevel();
        log.info("Current mapping level: {}", mappingLevel);
        List<LogisticsByLocationDto> data;
        switch (mappingLevel) {
            case 1:
                data = provinceRepo.findAllProvinces();
                break;
            case 2:
                data = districtRepo.findAllDistricts();
                break;
            case 3:
                data = subdistrictRepo.findAllSubdistricts();
                break;
            default:
                log.error("Invalid mapping level: {}", mappingLevel);
                throw new IllegalArgumentException("Invalid mapping level");
        }
        return provinceMapper.mapToLogisticsResponse(data);
    }

    @Override
    public List<LogisticsResponse> getLogisticsByDistrict(Long provinceId) {
        int mappingLevel = logisticConfig.getMappingLevel();
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
                throw new IllegalArgumentException("Invalid mapping level");
        }
        return provinceMapper.mapToLogisticsResponse(data);
    }

    @Override
    public List<LogisticsResponse> getLogisticsBySubdistrict(Long districtId) {
        int mappingLevel = logisticConfig.getMappingLevel();
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
                throw new IllegalArgumentException("Invalid mapping level");
        }
        return provinceMapper.mapToLogisticsResponse(data);
    }

    @Override
    public byte[] exportLogisticsToExcel() throws IOException {
        int mappingLevel = logisticConfig.getMappingLevel();
        log.info("Exporting logistics data with mapping level: {}", mappingLevel);

        List<LogisticExport> data;
        String sheetName;
        String[] headers;

        switch (mappingLevel) {
            case 1:
                data = provinceRepo.exportLevel1();
                sheetName = "Level 1 - Province";
                headers = MappingLevel.LEVEL_1.getHeaders();
                break;
            case 2:
                data = districtRepo.exportLevel2();
                sheetName = "Level 2 - District";
                headers = MappingLevel.LEVEL_2.getHeaders();
                break;
            case 3:
                data = subdistrictRepo.exportLevel3();
                sheetName = "Level 3 - Subdistrict";
                headers = MappingLevel.LEVEL_3.getHeaders();
                break;
            default:
                throw new IllegalArgumentException("Invalid mapping level: " + mappingLevel);
        }

        try (Workbook workbook = new XSSFWorkbook()) {
            var sheet = workbook.createSheet(sheetName);

            var headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                headerRow.createCell(i).setCellValue(headers[i]);
            }

            // Xuất dữ liệu vào Excel
            int rowIdx = 1;
            for (var row : data) {
                var excelRow = sheet.createRow(rowIdx++);
                int cellIdx = 0;

                excelRow.createCell(cellIdx++).setCellValue(row.getProvinceId());
                excelRow.createCell(cellIdx++).setCellValue(row.getProvinceName());

                if (mappingLevel >= 2) {
                    excelRow.createCell(cellIdx++).setCellValue(row.getDistrictId());
                    excelRow.createCell(cellIdx++).setCellValue(row.getDistrictName());
                }

                if (mappingLevel == 3) {
                    excelRow.createCell(cellIdx++).setCellValue(row.getSubdistrictId());
                    excelRow.createCell(cellIdx++).setCellValue(row.getSubdistrictName());
                }

                excelRow.createCell(cellIdx++).setCellValue(row.getFfmId() != null ? row.getFfmId() : 0);
                excelRow.createCell(cellIdx++).setCellValue(row.getFfmName());
                excelRow.createCell(cellIdx++).setCellValue(row.getLmId() != null ? row.getLmId() : 0);
                excelRow.createCell(cellIdx++).setCellValue(row.getLmName());
                excelRow.createCell(cellIdx++).setCellValue(row.getWhId() != null ? row.getWhId() : 0);
                excelRow.createCell(cellIdx).setCellValue(row.getWhName());
            }

            for (int i = 0; i < headers.length; i++) {
                sheet.autoSizeColumn(i);
            }

            // Ghi dữ liệu vào ByteArrayOutputStream và trả về byte
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        }
    }
}

