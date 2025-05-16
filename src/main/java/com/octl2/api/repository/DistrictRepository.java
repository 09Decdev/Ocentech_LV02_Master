package com.octl2.api.repository;

import com.octl2.api.entity.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DistrictRepository extends JpaRepository<District,Long> {
    @Query("SELECT d FROM District d WHERE d.provinceId = :provinceId")
    List<District> findByProvinceId(Long provinceId);
}
