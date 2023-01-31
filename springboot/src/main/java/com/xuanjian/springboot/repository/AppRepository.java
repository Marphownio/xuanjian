package com.xuanjian.springboot.repository;

import com.xuanjian.springboot.pojo.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppRepository extends JpaRepository<App,Long> {
}
