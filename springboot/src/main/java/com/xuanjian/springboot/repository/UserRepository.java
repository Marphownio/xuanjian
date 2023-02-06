package com.xuanjian.springboot.repository;

import com.xuanjian.springboot.pojo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    User findByUserName(String userID);
}
