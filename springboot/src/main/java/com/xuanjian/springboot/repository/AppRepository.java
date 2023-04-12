package com.xuanjian.springboot.repository;

import com.xuanjian.springboot.pojo.entity.App;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface AppRepository extends JpaRepository<App,Long> {

//    Set<App> findAllByNameLike(String appName);

    @Query(value = "Select * from apps where name like ?1 and id in (select MIN(id) from apps where md5 is not null group by md5 union select id from apps where md5 is null)", nativeQuery = true)
    Set<App> findByNameLikeWithNoMD5Repeat(String appName);

    @Query(value = "select id,first_upload_time,analyse_time,sandbox_state,analysis_state from apps where md5 in (select md5 from apps where id = ?1)", nativeQuery = true)
    Set<Object> findMD5Repeat(Long appId);
}
