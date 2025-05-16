package com.octl2.api.service.impl;

import com.octl2.api.config.LogisticConfig;
import com.octl2.api.entity.CfDefaultDelivery;
import com.octl2.api.entity.District;
import com.octl2.api.entity.Subdistrict;
import com.octl2.api.repository.CfDefaultDeliveryRepository;
import com.octl2.api.repository.DistrictRepository;
import com.octl2.api.repository.ProvinceRepository;
import com.octl2.api.repository.SubdistrictRepository;
import com.octl2.api.service.LocationLeverService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class LocationLevelServiceImpl implements LocationLeverService {
    private final ProvinceRepository provinceRepo;
    private final DistrictRepository districtRepo;
    private final SubdistrictRepository subdistrictRepo;
    private final CfDefaultDeliveryRepository cfDefaultDeliveryRepo;
    private final LogisticConfig logisticConfig;

    public int getLocationLever(Long locationId) {
        if (provinceRepo.existsById(locationId)) {
            return 1;
        }
        if (districtRepo.existsById(locationId)) {
            return 2;
        }
        if (subdistrictRepo.existsById(locationId)) {
            return 3;
        }
        return 0;
    }


    public void updateDefaultDelivery(Long locationId,
                                      Long ffmId, Long lmId, Long whId) {
        int locationLevel = getLocationLever(locationId);
        int mappingLevel = logisticConfig.getMappingLevel();
        log.info("Updating default delivery: locationLevel={}, mappingLevel={}, locationId={}, ffmId={}, lmId={}, whId={}",
                locationLevel, mappingLevel, locationId, ffmId, lmId, whId);

        switch (mappingLevel) {
            case 1:
                if (locationLevel != 1) {
                    throw new IllegalArgumentException(
                            "Không được phép: mapping cấp 1 chỉ cho phép cập nhật tỉnh");
                }
                updateNewDefaultDelivery(locationId, ffmId, lmId, whId);
                break;

            case 2:

                if (locationLevel == 1) {
                    updateDistrictofProvince(locationId, ffmId, lmId, whId);
                    districtRepo.findByProvinceId(locationId)
                            .forEach(d -> updateSubdistrictofDistrict(d.getDistrictId(), ffmId, lmId, whId));
                } else if (locationLevel == 2) {
                    updateNewDefaultDelivery(locationId, ffmId, lmId, whId);
                } else {
                    throw new IllegalArgumentException(
                            "Không được phép: cấp location phải là tỉnh hoặc huyện để cập nhật level 2");
                }
                break;

            case 3:
                if (locationLevel == 1) {
                    // Province -> District -> Subdistrict
                    updateDistrictofProvince(locationId, ffmId, lmId, whId);
                    districtRepo.findByProvinceId(locationId)
                            .forEach(d -> updateSubdistrictofDistrict(d.getDistrictId(), ffmId, lmId, whId));
                } else if (locationLevel == 2) {
                    // District -> Subdistrict
                    updateSubdistrictofDistrict(locationId, ffmId, lmId, whId);
                } else if (locationLevel == 3) {
                    // Subdistrict
                    updateNewDefaultDelivery(locationId, ffmId, lmId, whId);
                } else {
                    throw new IllegalArgumentException(
                            "Không được phép: cấp không hợp lệ để cập nhật level 3");
                }
                break;

            default:
                throw new IllegalStateException("Invalid mappingLevel: " + mappingLevel);
        }
    }

    private void updateNewDefaultDelivery(Long locationId, Long ffmId, Long lmId, Long whId) {
        CfDefaultDelivery entity = cfDefaultDeliveryRepo.findByLocationId(locationId)
                .orElseThrow(() -> new IllegalArgumentException("Location not found: " + locationId));
        entity.setFfmId(ffmId);
        entity.setLastmileId(lmId);
        entity.setWarehouseId(whId);
        cfDefaultDeliveryRepo.save(entity);

    }

    public void updateDistrictofProvince(Long locationId, Long ffmId, Long lmId, Long whId) {
        for (District district : districtRepo.findByProvinceId(locationId)) {
            updateNewDefaultDelivery(district.getDistrictId(), ffmId, lmId, whId);
        }
    }

    public void updateSubdistrictofDistrict(Long locationId, Long ffmId, Long lmId, Long whId) {
        for (Subdistrict sub : subdistrictRepo.findByDistrictId(locationId)) {
            updateNewDefaultDelivery(sub.getSubdistrictId(), ffmId, lmId, whId);
        }
    }

}
