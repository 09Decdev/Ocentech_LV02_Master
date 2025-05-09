package com.octl2.api.repository;

import com.octl2.api.dto.LogisticsByLocationDto;
import com.octl2.api.dto.response.LogisticsByProvinceDto;
import com.octl2.api.entity.CfDefaultDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsRepository extends JpaRepository<CfDefaultDelivery, Long> {

    @Query(value =
            "SELECT " +
                    "cf.cf_default_do_id, " +
                    "cf.org_id, " +
                    "cf.location_id AS province_id, " +
                    "p.name AS province_name, " +
                    "ffm.partner_id AS ffm_id, " +
                    "ffm.name AS ffm_name, " +
                    "lm.partner_id AS lastmile_id, " +
                    "lm.name AS lastmile_name, " +
                    "wh.warehouse_id AS warehouse_id, " +
                    "wh.warehouse_name AS warehouse_name, " +
                    "wh.province_name AS warehouse_province_name " +
                    "FROM cf_default_delivery cf " +
                    "LEFT JOIN lc_province p ON cf.location_id = p.province_id " +
                    "LEFT JOIN bp_partner ffm ON cf.ffm_id = ffm.partner_id AND ffm.partner_type = 122 " +
                    "LEFT JOIN bp_partner lm ON cf.lastmile_id = lm.partner_id AND lm.partner_type = 121 " +
                    "LEFT JOIN bp_warehouse wh ON cf.warehouse_id = wh.warehouse_id",
            nativeQuery = true)
    List<Object[]> findAllLogisticsByProvince();

    @Query(value =
            "SELECT" +
                    "d.district_id AS district_id" +
                    ",d.name AS district_name" +
                    ", ffm.partner_id AS ffm_id" +
                    ",ffm.name AS ffm_name" +
                    ",lm.partner_id AS lastmile_id" +
                    ",lm.name AS lastmile_name" +
                    ",wh.warehouse_id AS warehouse_id" +
                    ",wh.warehouse_name AS warehouse_name" +
                    ",wh.province_name AS warehouse_province_name" +
                    "FROM cf_default_delivery cf" +
                    "LEFT JOIN lc_district d ON cf.location_id = d.district_id" +
                    "LEFT JOIN bp_partner ffm ON cf.ffm_id=ffm.partner_id and ffm.partner_type=122" +
                    "LEFT JOIN bp_partner lm ON cf.lastmile_id=lm.partner_id and lm.partner_type=121" +
                    "LEFT JOIN bp_warehouse wh ON cf.warehouse_id = wh.warehouse_id" +
                    "WHERE d.province_id = :provinceId"
            , nativeQuery = true)
    List<LogisticsByProvinceDto> findAllDistrictLogisticsByProvinceId(@Param("provinceId") Long provinceId);


    // Level 1: Province
    @Query(value =
            "SELECT " +
                    "cf.cf_default_do_id, " +
                    "cf.org_id, " +
                    "cf.location_id AS location_id, " +
                    "p.name AS location_name, " +
                    "'Province' AS level_type, " +
                    "ffm.partner_id AS ffm_id, " +
                    "ffm.name AS ffm_name, " +
                    "lm.partner_id AS lm_id, " +
                    "lm.name AS lm_name, " +
                    "wh.warehouse_id AS wh_id, " +
                    "wh.warehouse_name AS wh_name " +
                    "FROM cf_default_delivery cf " +
                    "LEFT JOIN lc_province p " +
                    "ON cf.location_id = p.province_id " +
                    "AND :mappingLevel = 1 " +
                    "LEFT JOIN bp_partner ffm " +
                    "ON cf.ffm_id = ffm.partner_id " +
                    "AND ffm.partner_type = 122 " +
                    "LEFT JOIN bp_partner lm " +
                    "ON cf.lastmile_id = lm.partner_id " +
                    "AND lm.partner_type = 121 " +
                    "LEFT JOIN bp_warehouse wh " +
                    "ON cf.warehouse_id = wh.warehouse_id ",
            nativeQuery = true)
    List<LogisticsByLocationDto> findProvincesWithLogistics(
            @Param("mappingLevel") int mappingLevel);


    // Level 2: District
    @Query(value =
            "SELECT " +
                    "cf.cf_default_do_id, " +
                    "cf.org_id, " +
                    "cf.location_id AS location_id, " +
                    "d.name AS location_name, " +
                    "'District' AS level_type, " +
                    "ffm.partner_id AS ffm_id, " +
                    "ffm.name AS ffm_name, " +
                    "lm.partner_id AS lm_id, " +
                    "lm.name AS lm_name, " +
                    "wh.warehouse_id AS wh_id, " +
                    "wh.warehouse_name AS wh_name " +
                    "FROM cf_default_delivery cf " +
                    "LEFT JOIN lc_district d " +
                    "ON cf.location_id = d.district_id " +
                    "AND :mappingLevel = 2 " +
                    "LEFT JOIN bp_partner ffm " +
                    "ON cf.ffm_id = ffm.partner_id " +
                    "AND ffm.partner_type = 122 " +
                    "LEFT JOIN bp_partner lm " +
                    "ON cf.lastmile_id = lm.partner_id " +
                    "AND lm.partner_type = 121 " +
                    "LEFT JOIN bp_warehouse wh " +
                    "ON cf.warehouse_id = wh.warehouse_id " +
                    "WHERE d.province_id = :provinceId ",
            nativeQuery = true)
    List<LogisticsByLocationDto> findDistrictsWithLogisticsByProvince(
            @Param("provinceId") Long provinceId,
            @Param("mappingLevel") int mappingLevel);


    // Level 3: Subdistrict
    @Query(value =
            "SELECT " +
                    "cf.cf_default_do_id, " +
                    "cf.org_id, " +
                    "cf.location_id AS location_id, " +
                    "s.name AS location_name, " +
                    "'Subdistrict' AS level_type, " +
                    "ffm.partner_id AS ffm_id, " +
                    "ffm.name AS ffm_name, " +
                    "lm.partner_id AS lm_id, " +
                    "lm.name AS lm_name, " +
                    "wh.warehouse_id AS wh_id, " +
                    "wh.warehouse_name AS wh_name " +
                    "FROM cf_default_delivery cf " +
                    "LEFT JOIN lc_subdistrict s " +
                    "ON cf.location_id = s.subdistrict_id " +
                    "AND :mappingLevel = 3 " +
                    "LEFT JOIN bp_partner ffm " +
                    "ON cf.ffm_id = ffm.partner_id " +
                    "AND ffm.partner_type = 122 " +
                    "LEFT JOIN bp_partner lm " +
                    "ON cf.lastmile_id = lm.partner_id " +
                    "AND lm.partner_type = 121 " +
                    "LEFT JOIN bp_warehouse wh " +
                    "ON cf.warehouse_id = wh.warehouse_id " +
                    "WHERE s.district_id = :districtId ",
            nativeQuery = true)
    List<LogisticsByLocationDto> findSubdistrictsWithLogisticsByDistrict(
            @Param("districtId") Long districtId,
            @Param("mappingLevel") int mappingLevel);

}
