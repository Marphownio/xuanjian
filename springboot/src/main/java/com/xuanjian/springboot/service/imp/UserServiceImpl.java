package com.xuanjian.springboot.service.imp;

import com.xuanjian.springboot.pojo.entity.User;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.repository.UserRepository;
import com.xuanjian.springboot.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserRepository userRepository;

    @Resource
    private EncryptServiceImpl encryptService;

    @Override
    public ResultMessage userRegister(String userID, String userPW) throws NoSuchAlgorithmException {
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
                //session的创建
                HttpSession session=request.getSession(true);
                session.setAttribute("userName",userID);//给session添加属性
                session.setAttribute("userID",user.getId());
                String sessionId = session.getId();
                System.out.println(sessionId);
                //Cookie创建
                Cookie cookie = new Cookie("JSESSIONID", sessionId);
                cookie.setMaxAge(1 * 24 * 60 * 60);
                response.addCookie(cookie);
                return ResultMessage.SUCCESS;
            }
            else{
                return ResultMessage.FAILED;
            }
        }
    }
}
