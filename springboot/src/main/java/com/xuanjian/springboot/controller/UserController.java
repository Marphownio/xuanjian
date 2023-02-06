package com.xuanjian.springboot.controller;


import com.xuanjian.springboot.pojo.enums.ResultMessage;
import com.xuanjian.springboot.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public ResultMessage userRegister(@RequestParam("userName") String userID,@RequestParam("userPassword") String userPW) throws NoSuchAlgorithmException {
        return userService.userRegister(userID,userPW);
    }

    @PostMapping("/login")
    public ResultMessage userLogin(@RequestParam("userName") String userID, @RequestParam("userPassword") String userPW, HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException {
       return userService.userLogin(userID,userPW,request,response);
    }

}
