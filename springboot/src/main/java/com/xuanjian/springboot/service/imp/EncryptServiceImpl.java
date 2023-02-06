package com.xuanjian.springboot.service.imp;

import com.xuanjian.springboot.service.EncryptService;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

@Service
public class EncryptServiceImpl implements EncryptService {

    @Override
    public String generateSalt(){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random=new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<16;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    @Override
    public String encryptPasswordWithSalt(String salt,String password) throws NoSuchAlgorithmException {

        MessageDigest md = null;
        try{
            md = MessageDigest.getInstance("MD5");
        }catch (Exception e){
            System.out.println(e.toString());
            e.printStackTrace();
            return " ";
        }


        md.update(salt.getBytes());
        md.update(password.getBytes());

        byte[] results = md.digest();

        StringBuilder sb = new StringBuilder();
        for(byte bite : results) {
            sb.append(String.format("%02x", bite));
        }
        return sb.toString();
    }
}
