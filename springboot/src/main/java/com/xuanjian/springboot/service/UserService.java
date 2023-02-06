package com.xuanjian.springboot.service;

import com.xuanjian.springboot.pojo.enums.ResultMessage;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

public interface UserService {

    ResultMessage userRegister(String userID, String userPW) throws NoSuchAlgorithmException;

    ResultMessage userLogin(String userID, String userPW, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException;
}
