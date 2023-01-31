package com.xuanjian.springboot.service;

import com.xuanjian.springboot.pojo.entity.App;

import java.util.List;

public interface AppService {

    //根据ID获取APP的信息
    List<App> getAppInformById(Long appId);
}
