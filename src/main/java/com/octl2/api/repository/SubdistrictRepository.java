package com.octl2.api.repository;

import com.octl2.api.entity.Subdistrict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubdistrictRepository extends JpaRepository<Subdistrict,Long> {
    @Query("SELECT s FROM Subdistrict s WHERE s.districtId = :districtId")
    List<Subdistrict> findByDistrictId(Long districtId);
}
