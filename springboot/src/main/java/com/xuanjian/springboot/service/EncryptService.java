package com.xuanjian.springboot.service;

public interface EncryptService {

    String generateSalt();

    String encryptPasswordWithSalt(String salt,String password) throws java.security.NoSuchAlgorithmException;
}
