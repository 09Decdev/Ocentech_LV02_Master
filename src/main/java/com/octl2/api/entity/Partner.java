package com.octl2.api.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name="bp_partner")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Partner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "partner_type")
    private Integer partnerType;

    @Column(name = "name", length = 50)
    private String name;

    @Column(name = "shortname", length = 50)
    private String shortname;

    @Column(name = "contact", length = 50)
    private String contact;

    @Column(name = "phone", length = 50)
    private String phone;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address", length = 50)
    private String address;

    @Column(name = "modifyby")
    private Integer modifyBy;

    @Column(name = "modifydate")
    private LocalDateTime modifyDate;

    @Column(name = "logo_path_01", length = 300)
    private String logoPath01;

    @Column(name = "logo_path_02", length = 300)
    private String logoPath02;

    @Column(name = "type")
    private Integer type;

    @Column(name = "skill_level")
    private Integer skillLevel;

    @Column(name = "org_id")
    private Integer orgId;


}
