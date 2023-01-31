package com.xuanjian.springboot.service.imp;

import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.repository.AppRepository;
import com.xuanjian.springboot.service.AppService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

@Service
public class AppServiceImpl implements AppService {
    @Resource
    private AppRepository appRepository;

    @Override
    public List<App> getAppInformById(Long appId){
        return appRepository.findAllById(Collections.singleton(appId));
    }
}
