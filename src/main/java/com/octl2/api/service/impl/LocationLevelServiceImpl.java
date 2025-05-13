package com.octl2.api.service.impl;

import com.octl2.api.config.LogisticConfig;
import com.octl2.api.entity.CfDefaultDelivery;
import com.octl2.api.entity.District;
import com.octl2.api.entity.Subdistrict;
import com.octl2.api.repository.CfDefaultDeliveryRepository;
import com.octl2.api.repository.DistrictLevelLogisticsRepository;
import com.octl2.api.repository.ProvinceLevelLogisticsRepository;
import com.octl2.api.repository.SubdistrictLevelLogisticsRepository;
import com.octl2.api.service.LocationLeverService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationLevelServiceImpl implements LocationLeverService {
    private final ProvinceLevelLogisticsRepository provinceRepo;
    private final DistrictLevelLogisticsRepository districtRepo;
    private final SubdistrictLevelLogisticsRepository subdistrictRepo;
    private final CfDefaultDeliveryRepository cfDefaultDeliveryRepo;
    private final LogisticConfig logisticConfig;

    @Override
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

        int locationLever = getLocationLever(locationId);
        int levelMapping = logisticConfig.getMappingLevel();

        if (locationLever > levelMapping) {
            if (levelMapping == 2) {
                List<District> districts = districtRepo.findByProvinceId(locationId);
                for (District district : districts) {
                    updateNewDefaultDelivery(district.getDistrictId(), ffmId, lmId, whId);
                }
            } else if (levelMapping == 3) {
                List<District> districts = districtRepo.findByProvinceId(locationId);
                for (District district : districts) {
                    List<Subdistrict> subdistricts = subdistrictRepo.findByDistrictId(district.getDistrictId());
                    for (Subdistrict sub : subdistricts) {
                        updateNewDefaultDelivery(sub.getSubdistrictId(), ffmId, lmId, whId);
                    }
                }
            }
        } else if (locationLever < levelMapping) {
            throw new IllegalArgumentException("Không được phép cập nhật: cấp location nhỏ hơn level mapping");
        } else {
            updateNewDefaultDelivery(locationId, ffmId, lmId, whId);
        }
    }


    private void updateNewDefaultDelivery(Long locationId, Long ffmId, Long lmId, Long whId) {
        CfDefaultDelivery entity = cfDefaultDeliveryRepo.findByLocationId(locationId)
                .orElseThrow(() -> new RuntimeException("Not found default delivery for location:"));
        entity.setLocationId(locationId);
        entity.setFfmId(ffmId);
        entity.setLastmileId(lmId);
        entity.setWarehouseId(whId);
        cfDefaultDeliveryRepo.save(entity);
    }

}
