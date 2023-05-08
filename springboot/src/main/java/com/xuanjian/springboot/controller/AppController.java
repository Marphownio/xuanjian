package com.xuanjian.springboot.controller;


import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.entity.Screenshot;
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
@CrossOrigin(originPatterns = "*",allowCredentials = "true")
public class AppController {

    @Resource
    private AppService appService;

    @GetMapping("/getAppInformById")
    public Optional<App> getAppInformById(@RequestParam("appId") Long appId){
       return appService.getAppInformById(appId);
    }

    @GetMapping("/screenshot")
    public ResponseEntity<Set<String>> getScreenshotById(@RequestParam("appId") Long appId){
        return appService.getScreenshotById(appId);
    }
    @GetMapping("/screeshotImg")
    public ResponseEntity<byte[]> getScreenshotByUrl(@RequestParam("appId") Long appId,@RequestParam("screenShotName") String screenShotName) throws IOException {
        return appService.getScreenshotByUrl(appId,screenShotName);
    }
    @GetMapping("/appIcon")
    public ResponseEntity<byte[]> getAppIconById(@RequestParam("appId") Long appId) throws IOException {
        return appService.getAppIconById(appId);
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

    @GetMapping("/checkRepeatedMD5")
    public ResponseEntity checkRepeatedMD5(@RequestParam("appId") Long appId){
        return appService.checkMD5Repeat(appId);
    }

    @PostMapping("/recheckApp")
    public ResultMessage recheckApp(@RequestParam("appId") Long appId,HttpServletRequest request) throws IOException, InterruptedException {
        return appService.recheckAppById(appId,request);
    }
}
