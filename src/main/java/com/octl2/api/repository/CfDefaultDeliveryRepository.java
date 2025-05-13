package com.octl2.api.repository;

import com.octl2.api.entity.CfDefaultDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CfDefaultDeliveryRepository extends JpaRepository<CfDefaultDelivery,Long> {
    Optional<CfDefaultDelivery> findByLocationId(Long locationId);
}
