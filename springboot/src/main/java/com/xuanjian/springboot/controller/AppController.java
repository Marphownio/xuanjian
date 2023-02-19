package com.xuanjian.springboot.controller;


import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.service.AppService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping(value = "/app")
public class AppController {

    @Resource
    private AppService appService;

    @GetMapping("/getAppInformById")
    public Optional<App> getAppInformById(@RequestParam("appId") Long appId){
       return appService.getAppInformById(appId);
    }

    @PostMapping("/upload")
    public ResultMessage uploadNewApp(@RequestParam(value = "file",required = false) MultipartFile[] files, HttpServletRequest request) throws IOException, ParseException {
        return appService.userUploadApk(files,request);
    }

    @GetMapping("/getUploadHistory")
    public ResponseEntity<Set<App>> getUserUploadHistory(HttpServletRequest request){
        return appService.appsUploadByUser(request);
    }

    @GetMapping("/search")
    public ResponseEntity<Set<App>> searchAppByName(@RequestParam("appName") String appName){
        return appService.searchAppByName(appName);
    }
}
