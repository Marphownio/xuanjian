package com.xuanjian.springboot.service;

import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface AppService {

    ResultMessage userUploadApk(MultipartFile file,HttpServletRequest request) throws IOException, ParseException;

    //根据ID获取APP的信息
    List<App> getAppInformById(Long appId);
}
