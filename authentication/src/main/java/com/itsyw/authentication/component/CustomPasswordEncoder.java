package com.itsyw.authentication.component;

import com.itsyw.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.DigestUtils;

import java.nio.charset.StandardCharsets;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/10 9:32
 * @Version: 1.0
 * TODO: 自定义passwordEncoder
 */
@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {

//    private static final String SALT = "SYW-XQL";

    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//      MD5加密
        String md5Encode = DigestUtils.md5DigestAsHex((MD5Utils.SALT + charSequence.toString()).getBytes());
        log.info("encodeStr:{}----s:{}", md5Encode, s);
        // Bcrypt加密
        String bcryptEncode = encoder.encode(md5Encode);

        return encoder.matches(s, bcryptEncode);

    }

}
