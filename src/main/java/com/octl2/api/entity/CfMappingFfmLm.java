package com.octl2.api.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "cf_mapping_ffm_lm")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CfMappingFfmLm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mapping_id")
    private Long mappingId;

    @Column(name = "ffm_id")
    private Long ffmId;

    @Column(name = "lm_id")
    private Long lmId;
}