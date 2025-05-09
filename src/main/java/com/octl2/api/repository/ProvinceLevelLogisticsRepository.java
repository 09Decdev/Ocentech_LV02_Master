package com.octl2.api.repository;

import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.entity.CfDefaultDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceLevelLogisticsRepository extends JpaRepository<CfDefaultDelivery, Long> {

    @Query("SELECT cf.locationId as locationId, p.name as locationName, 'Province' as levelType, " +
            "ffm.partnerId as ffmId, ffm.name as ffmName, lm.partnerId as lmId, lm.name as lmName, " +
            "wh.warehouseId as whId, wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, Province p, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = p.id " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findAllProvinces();

    // Lấy logistics cho tất cả district thuộc 1 province
    @Query("SELECT d.districtId as locationId, d.name as locationName, 'District' as levelType, " +
            "ffm.partnerId as ffmId, ffm.name as ffmName, lm.partnerId as lmId, lm.name as lmName, " +
            "wh.warehouseId as whId, wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, District d, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = d.provinceId " +
            "AND d.provinceId = :provinceId " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findDistrictsByProvince(@Param("provinceId") Long provinceId);

    // Lấy logistics cho tất cả subdistrict thuộc 1 province
    @Query("SELECT s.subdistrictId as locationId, s.name as locationName, 'Subdistrict' as levelType, " +
            "ffm.partnerId as ffmId, ffm.name as ffmName, lm.partnerId as lmId, lm.name as lmName, " +
            "wh.warehouseId as whId, wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, Subdistrict s, District d, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = d.provinceId " +
            "AND s.districtId = d.districtId " +
            "AND d.provinceId = :provinceId " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findSubdistrictsByProvince(@Param("provinceId") Long provinceId);
}
