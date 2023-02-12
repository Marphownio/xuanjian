package com.xuanjian.springboot.repository;

import com.xuanjian.springboot.pojo.entity.Domain;
import com.xuanjian.springboot.pojo.enums.DomainType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DomainRepository extends JpaRepository<Domain,Long> {

    @Query(value = "Select count(*) as total from domain where domain_type & ?1 <> 0", nativeQuery = true)
    int countByFunction(int targetFunction);
}
