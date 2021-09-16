package com.itsyw.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/10 13:29
 * @Version: 1.0
 * TODO:
 */
public class MD5Utils {

    public static final String SALT = "SYW-XQL";

    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("sywai951130");
        System.out.println(encode);
    }

}
