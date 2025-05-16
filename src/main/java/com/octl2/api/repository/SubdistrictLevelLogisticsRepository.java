package com.octl2.api.repository;

import com.octl2.api.dto.LogisticExport;
import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.entity.CfDefaultDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubdistrictLevelLogisticsRepository extends JpaRepository<CfDefaultDelivery, Long> {

    @Query("SELECT cf.locationId as locationId, s.name as locationName, 'Subdistrict' as levelType, " +
            "ffm.partnerId as ffmId, ffm.name as ffmName, lm.partnerId as lmId, lm.name as lmName, " +
            "wh.warehouseId as whId, wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, Subdistrict s, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = s.subdistrictId " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findAllSubdistricts();

    // Lấy logistics cho tất cả subdistrict thuộc 1 district
    @Query("SELECT cf.locationId as locationId, s.name as locationName, 'Subdistrict' as levelType, " +
            "ffm.partnerId as ffmId, ffm.name as ffmName, lm.partnerId as lmId, lm.name as lmName, " +
            "wh.warehouseId as whId, wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, Subdistrict s, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = s.subdistrictId " +
            "AND s.districtId = :districtId " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findSubdistrictsByDistrict(@Param("districtId") Long districtId);

    @Query("SELECT" +
            " p.id AS provinceId, " +
            " p.name as provinceName," +
            " d.districtId AS districtId, " +
            " d.name as districtName," +
            " s.subdistrictId as subdistrictId," +
            " s.name AS subdistrictName," +
            " 'Level 3' as levelType, " +
            " ffm.partnerId as ffmId, " +
            " ffm.name as ffmName, " +
            " lm.partnerId as lmId, " +
            " lm.name as lmName, " +
            " wh.warehouseId as whId, " +
            " wh.warehouseName as whName" +
            " from CfDefaultDelivery cf " +
            " JOIN Subdistrict s ON cf.locationId = s.subdistrictId " +
            " JOIN District d ON s.districtId = d.districtId " +
            " JOIN Province p ON d.provinceId = p.id " +
            " LEFT JOIN Partner ffm ON cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            " LEFT JOIN Partner lm ON cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            " LEFT JOIN Warehourse wh ON cf.warehouseId = wh.warehouseId"
    )
    List<LogisticExport> exportLevel3();

}
