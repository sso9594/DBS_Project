package com.dbs.dbsproject.util;

import java.lang.reflect.Array;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class PasswordEncoder {
    public String encode(String pw){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(pw.getBytes());
            byte[] data = md.digest();
            return Arrays.toString(data);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return "Encoding 실패";
    }
}
