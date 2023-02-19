package com.xuanjian.springboot.repository;

import com.xuanjian.springboot.pojo.entity.Screenshot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Set;

public interface ScreenshotRepository extends JpaRepository<Screenshot,Long> {

    //Set<Screenshot> findByApp_Id(Long appId);

    @Query(value = "select store_path from screenshot where app_id = ?1", nativeQuery = true)
    Set<String> findStorePathByAppId(Long appId);
}
