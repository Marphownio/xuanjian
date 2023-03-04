package com.xuanjian.springboot.controller;


import com.xuanjian.springboot.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/domains")
public class DomainController {

    @Resource
    private DomainService domainService;

    @GetMapping("/getDomainsById")
    public ResponseEntity<Object> getDomainsByAppId(@RequestParam("appId") int appId){
        return domainService.getDomainsByAppId(appId);
    }
}
