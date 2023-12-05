package com.dbs.dbsproject.util;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

@Component
public class PasswordEncoder {
    public String encode(String pw) throws NoSuchAlgorithmException{
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(pw.getBytes());

        return bytesToHex(md.digest());
    }

    private String bytesToHex(byte[] bytes){
        StringBuilder builder = new StringBuilder();
        for(byte b : bytes){
            builder.append(String.format("%02x",b));
        }
        return builder.toString();
    }
}
