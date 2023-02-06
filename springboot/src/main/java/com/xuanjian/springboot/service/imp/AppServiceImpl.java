package com.xuanjian.springboot.service.imp;

import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.entity.User;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.repository.AppRepository;
import com.xuanjian.springboot.repository.UserRepository;
import com.xuanjian.springboot.service.AppService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class AppServiceImpl implements AppService {
    @Resource
    private AppRepository appRepository;

    @Resource
    private UserRepository userRepository;

    @Transactional  //开启事务
    @Override
    public ResultMessage userUploadApk(MultipartFile file,HttpServletRequest request) throws IOException, ParseException {
        HttpSession session=request.getSession();
        if(session.getAttribute("userName") == null || session.getAttribute("userID") == null){
            return ResultMessage.NOT_LOGIN;
        }
        else{
            Long userID = (Long) session.getAttribute("userID");
            //判断文件是否为空
            if(file == null) {
                return ResultMessage.FILE_EMPTY;
            }
            //获取文件名
            String apkName = file.getOriginalFilename();
            //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
            long apkSize=file.getSize();
            if (apkName == null || apkSize == 0) {
                return ResultMessage.FILE_EMPTY;
            }
            //获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            //创建app对象
            App newApk = new App();
            newApk.setPackageName(apkName);
            newApk.setFirstUploadTime(date);
            newApk.setSize(apkSize);
            //写入文件
            String filePath = "D:\\学习\\网络灰黑产研究\\挑战杯\\xuanjian\\springboot\\src\\main\\resources\\static\\apk\\"; // 上传后的路径
            filePath += apkName;
            newApk.setRestorePath(filePath);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(filePath);
                fos.write(file.getBytes()); // 写入文件
            } catch (Exception e) {
                e.printStackTrace();
                return ResultMessage.FAILED;
            }finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try{
                //新apk写入数据库
                appRepository.save(newApk);
                //创建用户
                Optional<User> user = userRepository.findById(userID);
                if(user.isPresent()){
                    user.get().getAppList().add(newApk);
                }
                userRepository.save(user.get());
            } catch (Exception e){
                return ResultMessage.FAILED;
            }
            return ResultMessage.SUCCESS;
        }

    }

    @Override
    public List<App> getAppInformById(Long appId){
        return appRepository.findAllById(Collections.singleton(appId));
    }
}
