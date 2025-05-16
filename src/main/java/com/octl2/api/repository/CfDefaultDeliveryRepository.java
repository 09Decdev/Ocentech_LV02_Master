package com.octl2.api.repository;

import com.octl2.api.entity.CfDefaultDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CfDefaultDeliveryRepository extends JpaRepository<CfDefaultDelivery, Long> {
    @Query(value = "SELECT * FROM cf_default_delivery   WHERE location_id = :locationId limit 1", nativeQuery = true)
    Optional<CfDefaultDelivery> findByLocationId(@Param("locationId") Long locationId);
}
