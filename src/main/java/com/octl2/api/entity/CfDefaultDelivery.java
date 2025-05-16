package com.octl2.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "cf_default_delivery")
public class CfDefaultDelivery {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cf_default_do_id")
    private Long cfDefaultDoId;

    @Column(name = "org_id")
    private Long orgId;

    @Column(name = "location_id")
    private Long locationId;

    @Column(name = "ffm_id")
    private Long ffmId;

    @Column(name = "lastmile_id")
    private Long lastmileId;

    @Column(name = "warehouse_id")
    private Long warehouseId;
}
