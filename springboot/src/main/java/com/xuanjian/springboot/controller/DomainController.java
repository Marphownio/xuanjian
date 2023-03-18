package com.xuanjian.springboot.controller;


import com.xuanjian.springboot.service.DomainService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/domains")
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
public class DomainController {

    @Resource
    private DomainService domainService;

    @GetMapping("/getDomainsById")
    public ResponseEntity<Object> getDomainsByAppId(@RequestParam("appId") int appId){
        return domainService.getDomainsByAppId(appId);
    }
}
