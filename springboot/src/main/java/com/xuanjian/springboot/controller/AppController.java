package com.xuanjian.springboot.controller;


import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.service.AppService;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
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

    @PostMapping("/upload")
    public ResultMessage uploadNewApp(@RequestParam(value = "file",required = false) MultipartFile file, HttpServletRequest request) throws IOException, ParseException {
        return appService.userUploadApk(file,request);
    }
}
