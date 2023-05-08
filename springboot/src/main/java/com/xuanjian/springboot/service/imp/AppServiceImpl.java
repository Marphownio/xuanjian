package com.xuanjian.springboot.service.imp;

import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.entity.User;
import com.xuanjian.springboot.pojo.enums.AnalysisState;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.pojo.enums.SandboxState;
import com.xuanjian.springboot.repository.AppRepository;
import com.xuanjian.springboot.repository.ScreenshotRepository;
import com.xuanjian.springboot.repository.UserRepository;
import com.xuanjian.springboot.service.AppService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;
import java.io.*;
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
    private ScreenshotRepository screenshotRepository;

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
        Process p = null;
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
            df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
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
            String cmd = "chmod o+w /home/scam/seaweedfs/data/mount/2023/";
            cmd += tempApk.getId();
            p = Runtime.getRuntime().exec(cmd);
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
        p.exitValue();
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
    public ResponseEntity<Set<String>> getScreenshotById(Long appId){
        return ResponseEntity.ok(screenshotRepository.findStorePathByAppId(appId));
    }

    @Override
    public ResponseEntity<Set<App>> searchAppByName(String appName){
        return ResponseEntity.ok(appRepository.findByNameLikeWithNoMD5Repeat("%"+appName+"%"));
    }

    @Override
    public ResponseEntity getAppIconById(Long appId) throws IOException {
        byte[] bytes;
        // 设置一个head
        HttpHeaders headers = new HttpHeaders();
        //设置ContentType的值 IMAGE_JPEG在浏览器返回图片
        headers.setContentType(MediaType.IMAGE_PNG);
        try{
            File file = new File("/home/scam/seaweedfs/data/mount/2023/"+appId+"/icon.png");
            // 内容是字节流
            FileInputStream fis = new FileInputStream(file);
            bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
        }catch (Exception e){
            return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity getScreenshotByUrl(Long appId,String screenShotName) throws IOException {
        byte[] bytes;
        // 设置一个head
        HttpHeaders headers = new HttpHeaders();
        //设置ContentType的值 IMAGE_JPEG在浏览器返回图片
        headers.setContentType(MediaType.IMAGE_PNG);
        try{
            File file = new File("/home/scam/seaweedfs/data/mount/2023/"+appId+"/"+screenShotName);
            // 内容是字节流
            FileInputStream fis = new FileInputStream(file);
            bytes = new byte[fis.available()];
            fis.read(bytes);
            fis.close();
        }catch (Exception e){
            return new ResponseEntity<>(new HashSet<>(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(bytes, headers, HttpStatus.OK);
    }

    @Override
    public ResponseEntity checkMD5Repeat(Long appId){
        return new ResponseEntity<>(appRepository.findMD5Repeat(appId), HttpStatus.OK);
    }

    @Override
    public ResultMessage recheckAppById(Long appId,HttpServletRequest request) throws IOException, InterruptedException {
        Optional<App> currentApp = appRepository.findById(appId);
        if(!currentApp.isPresent()){
            return ResultMessage.FAILED;
        }
        Set<App> appList = currentApp.get().getRecheckAppList();
        if(appList.size()<3){
            if(reInsertAppByID(appId, request)==true){
                return ResultMessage.SUCCESS;
            }
            else {
                return ResultMessage.FAILED;
            }
        }
        else{
            return ResultMessage.OVER_LIMIT;
        }
    }

    @Transactional
    @Override
    public Boolean reInsertAppByID(Long appId,HttpServletRequest request) throws IOException, InterruptedException {
        HttpSession session = request.getSession();
        HashSet<Long> apkIDSet = new HashSet<Long>();
        if(session.getAttribute("tempUploadSet") != null){
            apkIDSet = (HashSet<Long>) session.getAttribute("tempUploadSet");
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        df.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
        String newDate = df.format(new Date());
        Process p = null;
        Optional<App> currentAppOp = appRepository.findById(appId);
        if(!currentAppOp.isPresent()){
            return false;
        }
        App currentApp = currentAppOp.get();
        App newApp = new App();
        newApp.setSize(currentApp.getSize());
        newApp.setPackageName(currentApp.getPackageName());
        newApp.setFirstUploadTime(newDate);
        newApp.setSandboxState(SandboxState.MD5_REPEATED);
        newApp.setAnalysisState(AnalysisState.UPLOADED);
        newApp.setRestorePath("temp");
        App tempApk;

        //新apk写入数据库
        try {
            tempApk = appRepository.save(newApp);
            if (session.getAttribute("userName") != null && session.getAttribute("userID") != null) {
                Long userID = (Long) session.getAttribute("userID");
                Optional<User> user = userRepository.findById(userID);
                if (user.isPresent()) {
                    user.get().getAppList().add(tempApk);
                    userRepository.save(user.get());
                }
            }
            else{
                apkIDSet.add(tempApk.getId());
            }
        } catch (Exception e) {
            return false;
        }

        //写入再次分析表
        try {
            currentApp.getRecheckAppList().add(tempApk);
            appRepository.save(currentApp);
        } catch (Exception e) {
            return false;
        }

        //创建新文件夹
        String directoryPath = "/home/scam/seaweedfs/data/mount/2023/";
        directoryPath += tempApk.getId();
        File dirMaker = new File(directoryPath);
        if(!dirMaker.mkdir()){
            return false;
        }

        //修改文件夹权限，创建apk的硬链接
        String cmd = "chmod o+w /home/scam/seaweedfs/data/mount/2023/";
        cmd += tempApk.getId();
        p = Runtime.getRuntime().exec(cmd);
        p.waitFor();
        String filePath = directoryPath; // 上传后的路径
        filePath += "/";
        filePath += tempApk.getId();
        filePath += ".apk";
        String cmdForIn = "ln ";
        cmdForIn+=currentApp.getRestorePath();
        cmdForIn+=" ";
        cmdForIn+=filePath;
        p = Runtime.getRuntime().exec(cmdForIn);
        p.waitFor();

        //设置temp的新存储路径
        tempApk.setRestorePath(filePath);
        try {
            //apk写入存储路径
            appRepository.save(tempApk);
        } catch (Exception e) {
            return false;
        }
        if(!apkIDSet.isEmpty()){
            session.setAttribute("tempUploadSet", apkIDSet);
        }
        return true;
    }
}
