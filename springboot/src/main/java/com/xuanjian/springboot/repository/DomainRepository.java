package com.xuanjian.springboot.repository;

import com.xuanjian.springboot.pojo.entity.Domain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain,Long> {
}
