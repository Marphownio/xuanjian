package com.xuanjian.springboot.controller;

import com.xuanjian.springboot.pojo.entity.DomainInform;
import com.xuanjian.springboot.service.AppService;
import com.xuanjian.springboot.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/overview")
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
public class OverviewController {

    @Resource
    private AppService appService;

    @Resource
    private DomainService domainService;

    @GetMapping("/apps")
    public int appOverview(){
        return appService.numberOfApks();
    }

    @GetMapping("/domains")
    public ResponseEntity<DomainInform> domainOverview(){
        return domainService.domainOverview();
    }
}
