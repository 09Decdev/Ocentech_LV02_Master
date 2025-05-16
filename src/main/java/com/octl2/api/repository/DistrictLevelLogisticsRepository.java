package com.octl2.api.repository;

import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.dto.LogisticExport;
import com.octl2.api.entity.CfDefaultDelivery;
import com.octl2.api.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DistrictLevelLogisticsRepository extends JpaRepository<CfDefaultDelivery, Long> {

    @Query("SELECT cf.locationId as locationId, d.name as locationName, 'District' as levelType, " +
            "ffm.partnerId as ffmId, ffm.name as ffmName, lm.partnerId as lmId, lm.name as lmName, " +
            "wh.warehouseId as whId, wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, District d, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = d.districtId " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findAllDistricts();

    // Lấy logistics cho tất cả subdistrict thuộc 1 district
    @Query("SELECT s.subdistrictId as locationId, s.name as locationName, 'Subdistrict' as levelType, " +
            "ffm.partnerId as ffmId, ffm.name as ffmName, lm.partnerId as lmId, lm.name as lmName, " +
            "wh.warehouseId as whId, wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, Subdistrict s, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = s.districtId " +
            "AND s.districtId = :districtId " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findSubdistrictsByDistrict(@Param("districtId") Long districtId);

    // Lấy logistics cho tất cả subdistrict
    @Query("SELECT s.subdistrictId as locationId," +
            " s.name as locationName," +
            " 'Subdistrict' as levelType, " +
            "ffm.partnerId as ffmId," +
            " ffm.name as ffmName, " +
            "lm.partnerId as lmId," +
            " lm.name as lmName, " +
            "wh.warehouseId as whId, " +
            "wh.warehouseName as whName " +
            "FROM CfDefaultDelivery cf, Subdistrict s, Partner ffm, Partner lm, Warehourse wh " +
            "WHERE cf.locationId = s.districtId " +
            "AND cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            "AND cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            "AND cf.warehouseId = wh.warehouseId")
    List<LogisticsByLocationDto> findAllSubdistricts();

    @Query("SELECT" +
            " p.id AS provinceId, " +
            " p.name as provinceName," +
            " d.districtId AS districtId, " +
            " d.name as districtName," +
//            " NULL as subdistrictId," +
//            " NULL AS subdistrictName ," +
            " 'Level 2' as levelType, " +
            " ffm.partnerId as ffmId, " +
            " ffm.name as ffmName, " +
            " lm.partnerId as lmId, " +
            " lm.name as lmName, " +
            " wh.warehouseId as whId, " +
            " wh.warehouseName as whName" +
            " from CfDefaultDelivery cf " +
            " JOIN District d ON cf.locationId = d.districtId " +
            " JOIN Province p ON d.provinceId = p.id " +
            " LEFT JOIN Partner ffm ON cf.ffmId = ffm.partnerId AND ffm.partnerType = 122 " +
            " LEFT JOIN Partner lm ON cf.lastmileId = lm.partnerId AND lm.partnerType = 121 " +
            " LEFT JOIN Warehourse wh ON cf.warehouseId = wh.warehouseId"
    )
    List<LogisticExport> exportLevel2();


}

