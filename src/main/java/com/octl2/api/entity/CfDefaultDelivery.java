package com.octl2.api.entity;

import javax.persistence.*;

@Entity
public class CfDefaultDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cf_default_do_id")
    private Long cfDefaultDoId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "location_id")
    private Long locationId; // Có thể là province_id, district_id hoặc subdistrict_id

    @Column(name = "ffm_id")
    private Long ffmId; // ID của FFM trong bp_partner

    @Column(name = "lastmile_id")
    private Long lastmileId; // ID của LM trong bp_partner

    @Column(name = "warehouse_id")
    private Long warehouseId; // ID của Warehouse

    @Column(name = "createby")
    private Long createBy;

    @Column(name = "createdate")
    private java.time.LocalDateTime createDate;

    @Column(name = "modifyby")
    private Long modifyBy;

    @Column(name = "modifydate")
    private java.time.LocalDateTime modifyDate;
}
