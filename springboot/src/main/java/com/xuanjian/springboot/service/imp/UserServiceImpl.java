package com.xuanjian.springboot.service.imp;

import com.xuanjian.springboot.pojo.entity.User;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.repository.UserRepository;
import com.xuanjian.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.util.Optional;
import java.util.Random;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private JavaMailSender sender; // 引入Spring Mail依赖后，会自动装配到IOC容器。用来发送邮件

    @Resource
    private EncryptServiceImpl encryptService;

    @Override
    public ResultMessage userRegister(String userID, String userPW, String userEmail) throws NoSuchAlgorithmException {
        User user = userRepository.findByUserName(userID);
        if(user != null){
            return ResultMessage.USERNAME_EXIST;
        }
        else{
            String userSalt = encryptService.generateSalt();
            String encryptedPassword = encryptService.encryptPasswordWithSalt(userSalt,userPW);
            User newUser = new User();
            newUser.setUserName(userID);
            newUser.setUserPassword(encryptedPassword);
            newUser.setUserSalt(userSalt);
            newUser.setUserEmail(userEmail);
            try{
                userRepository.save(newUser);
            } catch (Exception e){
                return ResultMessage.FAILED;
            }
            return ResultMessage.SUCCESS;
        }
    }

    @Override
    public ResultMessage userLogin(String userID, String userPW, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
        User user = userRepository.findByUserName(userID);
        if(user == null){
            return ResultMessage.USER_NOT_EXIST;
        }
        else{
            if(user.getUserPassword().equals(encryptService.encryptPasswordWithSalt(user.getUserSalt(),userPW))){
                HttpSession oldSession=request.getSession();
                oldSession.invalidate();
                //session的创建
                HttpSession session=request.getSession();
                session.setAttribute("userName",userID);//给session添加属性
                session.setAttribute("userID",user.getId());
                return ResultMessage.SUCCESS;
            }
            else{
                return ResultMessage.FAILED;
            }
        }
    }

    @Override
    public ResultMessage userLogout(HttpServletRequest request){
        try{
            HttpSession session=request.getSession();
            if(session.getAttribute("userName") == null || session.getAttribute("userID") == null){
                session.invalidate();
                return ResultMessage.NOT_LOGIN;
            }
            session.invalidate();
        }catch (Exception e){
            return ResultMessage.FAILED;
        }
        return ResultMessage.SUCCESS;
    }

    @Override
    public String userGenerateCode(){
        String str="0123456789";
        Random randomCode=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<4;i++){
            int number=randomCode.nextInt(10);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public String userForgetPassword(String userName, HttpServletRequest request){
        HttpSession session=request.getSession();
        session.setAttribute("userName",userName);
        User user = userRepository.findByUserName(userName);
        if(user == null){
            return "USER_NOT_EXIST";
        }
        String userEmail = user.getUserEmail();
        String code = userGenerateCode();
        session.setAttribute("code",code);
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("[黑产APP检测]验证码"); // 发送邮件的标题
        message.setText("登录操作，验证码："+ code + "，切勿将验证码泄露给他人，本条验证码有效期24h。"); // 发送邮件的内容
        message.setTo(userEmail); // 指定要接收邮件的用户邮箱账号
        message.setFrom("1135632411@qq.com"); // 发送邮件的邮箱账号，注意一定要和配置文件中的一致！
        try {
            sender.send(message); // 调用send方法发送邮件即可
        }catch (Exception e){
            return "FAILED";
        }
        return userEmail;
    }

    @Override
    public ResultMessage userResetPassword(String userPW, String userVerifyCode, HttpServletRequest request) throws NoSuchAlgorithmException {
        HttpSession session=request.getSession();
        if(session.getAttribute("userName") == null || session.getAttribute("code") == null){
            return ResultMessage.FAILED;
        }
        String code = (String) session.getAttribute("code");
        if(!code.equals(userVerifyCode)){
            return ResultMessage.VERIFY_CODE_ERROR;
        }
        String userName = (String) session.getAttribute("userName");
        User user = userRepository.findByUserName(userName);
        if(user == null){
            return ResultMessage.FAILED;
        }
        String userSalt = user.getUserSalt();
        String newPassword = encryptService.encryptPasswordWithSalt(userSalt,userPW);
        user.setUserPassword(newPassword);
        try{
            userRepository.save(user);
        } catch (Exception e){
            return ResultMessage.FAILED;
        }
        session.invalidate();
        return ResultMessage.SUCCESS;
    }

    @Override
    public ResultMessage userIfLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        if(session.getAttribute("userName") == null || session.getAttribute("userID") == null){
            return ResultMessage.NOT_LOGIN;
        }
        else return ResultMessage.ALREADY_LOGIN;
    }
}
