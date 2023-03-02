package com.xuanjian.springboot.service;

import com.xuanjian.springboot.pojo.entity.App;
import com.xuanjian.springboot.pojo.enums.ResultMessage;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;
import java.util.HashSet;

public interface UserService {

    ResultMessage userRegister(String userID, String userPW, String userEmail) throws NoSuchAlgorithmException;

    ResultMessage userLogin(String userID, String userPW, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException;

    ResultMessage userLogout(HttpServletRequest request);

    String userForgetPassword(String userName, HttpServletRequest request);

    String userGenerateCode();

    ResultMessage userResetPassword(String userPW, String userVerifyCode, HttpServletRequest request) throws NoSuchAlgorithmException;

    ResultMessage userIfLogin(HttpServletRequest request);
}

