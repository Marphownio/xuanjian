package com.xuanjian.springboot.controller;


import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.service.AppService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @Resource
    private AppService appService;

    @GetMapping("/getAppInformById/{appId}")
    public List<App> getAppInformById(@PathVariable("appId") Long appId){
       return appService.getAppInformById(appId);
    }

    @PutMapping("/upload")
    public ResultMessage uploadNewApp(){
        return ResultMessage.SUCCESS;
    }
}
