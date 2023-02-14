package com.xuanjian.springboot.repository;

import com.xuanjian.springboot.pojo.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AppRepository extends JpaRepository<App,Long> {

    Set<App> findAllByNameLike(String appName);
}
