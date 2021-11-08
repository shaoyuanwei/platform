package com.itsyw.authentication.component.factory;

import com.itsyw.authentication.component.CustomPasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.*;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/26 15:16
 * @Version: 1.0
 * TODO:
 */
public class CustomPasswordEncoderFactories {

    public static PasswordEncoder createDelegatingPasswordEncoder() {
        String encodingId = "bcrypt";
        Map<String, PasswordEncoder> encoders = new HashMap();
        encoders.put(encodingId, new BCryptPasswordEncoder());
        encoders.put("pbkdf2", new Pbkdf2PasswordEncoder());
        encoders.put("scrypt", new SCryptPasswordEncoder());
        return new CustomPasswordEncoder(encodingId, encoders);
    }

    private CustomPasswordEncoderFactories() {
    }
}
