package com.xuanjian.springboot.service;

import com.xuanjian.springboot.pojo.entity.DomainInform;
import org.springframework.http.ResponseEntity;

public interface DomainService {

    ResponseEntity<DomainInform> domainOverview();
}
