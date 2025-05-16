package com.octl2.api.repository;

import com.octl2.api.entity.Warehourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WarehouseRepository extends JpaRepository<Warehourse,Long> {
    @Query("SELECT w FROM Warehourse w WHERE w.ffmId = :ffmId")
    List<Warehourse> findWarehourseByFfmId(@Param("ffmId") Long ffmId);
}
