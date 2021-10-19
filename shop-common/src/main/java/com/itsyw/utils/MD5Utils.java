package com.itsyw.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.util.DigestUtils;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/10 13:29
 * @Version: 1.0
 * TODO:
 */
public class MD5Utils {

    public static final String SALT = "SYW-XQL";

    public static void main(String[] args) {
        String secret = "secret";
        String s = DigestUtils.md5DigestAsHex(secret.getBytes());
        String deg = DigestUtils.md5DigestAsHex(s.getBytes());
        System.out.println(deg);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(deg);
        System.out.println(encode);
    }

}
