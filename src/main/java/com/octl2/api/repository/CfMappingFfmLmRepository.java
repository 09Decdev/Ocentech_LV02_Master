package com.octl2.api.repository;

import com.octl2.api.entity.CfMappingFfmLm;
import com.octl2.api.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CfMappingFfmLmRepository extends JpaRepository<CfMappingFfmLm,Long> {
    @Query("SELECT p FROM CfMappingFfmLm m JOIN Partner p ON m.lmId = p.partnerId " +
            "WHERE m.ffmId = :ffmId AND p.partnerType = 121")
    List<Partner>findLmsByFfmId(Long ffmId);
}
