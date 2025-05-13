package com.octl2.api.repository;

import com.octl2.api.entity.Partner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PartnerRepository extends JpaRepository<Partner,Long> {
    @Query("SELECT p FROM Partner p WHERE p.partnerId = :ffmId AND p.partnerType = 122")
    Partner findFfmById(@Param("ffmId") Long ffmId);
}
