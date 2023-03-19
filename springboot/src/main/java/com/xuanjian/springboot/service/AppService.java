package com.xuanjian.springboot.service;

import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.entity.Screenshot;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.Optional;
import java.util.Set;

public interface AppService {

    int numberOfApks();

    ResultMessage userUploadApk(MultipartFile[] files,HttpServletRequest request) throws IOException, ParseException;

    //根据ID获取APP的信息
    Optional<App> getAppInformById(Long appId);

    ResponseEntity<Set<String>> getScreenshotById(Long appId);

    ResponseEntity<Set<App>> appsUploadByUser(HttpServletRequest request);

    ResponseEntity<Set<App>> searchAppByName(String appName);

    ResponseEntity<byte[]> getAppIconById(Long appId) throws IOException;

    ResponseEntity<byte[]> getScreenshotByUrl(Long appId,String screenShotName) throws IOException;
}
