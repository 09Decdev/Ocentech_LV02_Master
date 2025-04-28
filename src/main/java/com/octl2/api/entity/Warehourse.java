package com.octl2.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "bp_warehouse")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Warehourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "warehouse_id")
    private Long warehouseId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "ffm_id")
    private Long ffmId;

    @Column(name = "warehouse_name")
    private String warehouseName;

    @Column(name = "warehouse_shortname")
    private String warehouseShortname;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_phone")
    private String contactPhone;

    @Column(name = "address")
    private String address;

    @Column(name = "full_address")
    private String fullAddress;

    @Column(name = "wards_id")
    private Long wardsId;

    @Column(name = "wards_code")
    private String wardsCode;

    @Column(name = "wards_name")
    private String wardsName;

    @Column(name = "district_id")
    private Long districtId;

    @Column(name = "district_code")
    private String districtCode;

    @Column(name = "district_name")
    private String districtName;

    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "province_code")
    private String provinceCode;

    @Column(name = "province_name")
    private String provinceName;

    @Column(name = "email")
    private String email;

    @Column(name = "is_edit_wh")
    private Integer isEditWh;

    @Column(name = "ismain")
    private Integer ismain;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "is_active")
    private Integer isActive;

    @Column(name = "createby")
    private Integer createBy;

    @Column(name = "createdate")
    private LocalDateTime createDate;

    @Column(name = "modifyby")
    private Integer modifyBy;

    @Column(name = "modifydate")
    private LocalDateTime modifyDate;

    @Column(name = "wh_code_inpartner", length = 200)
    private String whCodeInPartner;


}
