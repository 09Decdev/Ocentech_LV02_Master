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
    @Column(name = "partner_id")
    private Long partnerId;

    @Column(name = "partner_type")
    private Long partnerType;

    @Column(name = "name")
    private String name;

    @Column(name = "shortname")
    private String shortname;

}
