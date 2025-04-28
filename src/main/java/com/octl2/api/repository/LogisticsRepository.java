package com.octl2.api.repository;

import com.octl2.api.entity.CfDefaultDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LogisticsRepository extends JpaRepository<CfDefaultDelivery,Long> {
    @Query(value = "Select " +
            "p.province_id as id"+
            ",p.name"+
            ",p.shortname"+
            ",p.dcsr as description"+
            ",ffm.id as ffm_id"+
            ",ffm.name as ffm_name"+
            ",lm.id AS lastmile_id"+
            ",lm.name AS lastmile_name"+
            ", wh.id AS warehouse_id"+
            ",wh.name AS warehouse_name"+
            "from lc_province as p"+
            "  LEFT JOIN bp_partner ffm ON ffm.province_id = p.province_id AND ffm.pn_type = 122\n" +
            "            LEFT JOIN bp_partner lm ON lm.province_id = p.province_id AND lm.pn_type = 121\n" +
            "            LEFT JOIN bp_warehouse wh ON wh.province_id = p.province_id"
            ,nativeQuery = true)
    List<Object>findAllLogisticsByDistrict();

    @Query(value = "SELECT " +
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

}
