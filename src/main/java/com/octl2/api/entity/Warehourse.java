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

}
