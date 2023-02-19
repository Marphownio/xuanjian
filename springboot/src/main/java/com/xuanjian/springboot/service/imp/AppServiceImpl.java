package com.xuanjian.springboot.service.imp;

import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.entity.User;
import com.xuanjian.springboot.pojo.enums.AnalysisState;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.pojo.enums.SandboxState;
import com.xuanjian.springboot.repository.AppRepository;
import com.xuanjian.springboot.repository.UserRepository;
import com.xuanjian.springboot.service.AppService;
import com.xuanjian.springboot.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.File;
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

    @Resource
    private UserService userService;

    @Override
    public int numberOfApks(){
        return (int) appRepository.count();
    }

    @Transactional  //开启事务
    @Override
    public ResultMessage userUploadApk(MultipartFile[] files,HttpServletRequest request) throws IOException, ParseException {
        HttpSession session = request.getSession();
        HashSet<Long> apkIDSet = new HashSet<Long>();
        if(session.getAttribute("tempUploadSet") != null){
            apkIDSet = (HashSet<Long>) session.getAttribute("tempUploadSet");
        }
        if(files.length == 0) {
            return ResultMessage.FILE_EMPTY;
        }
        Boolean emptyFlag = Boolean.FALSE;
        for(MultipartFile file : files) {
            //获取文件名
            String apkName = file.getOriginalFilename();
            //进一步判断文件是否为空（即判断其大小是否为0或其名称是否为null）
            long apkSize = file.getSize();
            if (apkName == null || apkSize == 0) {
                emptyFlag = Boolean.TRUE;
                continue;
            }
            //获取当前时间
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
            String date = df.format(new Date());
            //创建app对象
            App newApk = new App();
            newApk.setPackageName(apkName);
            newApk.setFirstUploadTime(date);
            newApk.setSize(apkSize);
            newApk.setSandboxState(SandboxState.UPLOADED);
            newApk.setAnalysisState(AnalysisState.UPLOADED);
            newApk.setRestorePath("temp");
            App tempApk;
            try {
                //新apk写入数据库
                tempApk = appRepository.save(newApk);
                if (session.getAttribute("userName") != null && session.getAttribute("userID") != null) {
                    Long userID = (Long) session.getAttribute("userID");
                    Optional<User> user = userRepository.findById(userID);
                    if (user.isPresent()) {
                        user.get().getAppList().add(tempApk);
                    }
                    userRepository.save(user.get());
                }
                else{
                    apkIDSet.add(tempApk.getId());
                }
            } catch (Exception e) {
                return ResultMessage.FAILED;
            }
            //写入文件
            //创建文件夹
            String directoryPath = "/home/scam/seaweedfs/data/mount/2023/";
            directoryPath += tempApk.getId();
            File dirMaker = new File(directoryPath);
            if(!dirMaker.mkdir()){
                return ResultMessage.FAILED;
            }
            //写入文件
            String filePath = directoryPath; // 上传后的路径
            filePath += "/";
            filePath += tempApk.getId();
            filePath += ".apk";
            tempApk.setRestorePath(filePath);
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(filePath);
                fos.write(file.getBytes()); // 写入文件
            } catch (Exception e) {
                e.printStackTrace();
                return ResultMessage.FAILED;
            } finally {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                //apk写入存储路径
                appRepository.save(tempApk);
            } catch (Exception e) {
                return ResultMessage.FAILED;
            }
        }
        if(!apkIDSet.isEmpty()){
            session.setAttribute("tempUploadSet", apkIDSet);
        }
        if(emptyFlag == Boolean.TRUE){
            return ResultMessage.PART_SUCCESS;
        }
        else return ResultMessage.SUCCESS;
    }

    @Override
    public ResponseEntity<Set<App>> appsUploadByUser(HttpServletRequest request){
        HttpSession session=request.getSession();
        Set<App> appList;
        if(session.getAttribute("userName") == null || session.getAttribute("userID") == null){
            if(session.getAttribute("tempUploadSet") == null)
                return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);
            HashSet<Long> apkIDSet = (HashSet<Long>) session.getAttribute("tempUploadSet");
            if(apkIDSet.isEmpty())
                return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);
            appList = new HashSet<App>();
            for(Long apkID : apkIDSet){
                Optional<App> tempApp = appRepository.findById(apkID);
                if(!tempApp.isPresent()) continue;
                appList.add(tempApp.get());
            }
        }
        else{
            Long userID = (Long) session.getAttribute("userID");
            Optional<User> currentUser = userRepository.findById(userID);
            if(!currentUser.isPresent()){
                return new ResponseEntity<>(new HashSet<>(), HttpStatus.EXPECTATION_FAILED);
            }
            appList = currentUser.get().getAppList();
        }
        if (appList.isEmpty()){
            return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(appList, HttpStatus.OK);
    }

    @Override
    public Optional<App> getAppInformById(Long appId){
        return appRepository.findById(appId);
    }

    @Override
    public ResponseEntity<Set<App>> searchAppByName(String appName){
        return ResponseEntity.ok(appRepository.findAllByNameLike("%"+appName+"%"));
    }
}
