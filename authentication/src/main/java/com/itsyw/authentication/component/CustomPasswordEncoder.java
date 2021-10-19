package com.itsyw.authentication.component;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: YuanWei Shao
 * @Date: 2021/9/10 9:32
 * @Version: 1.0
 * TODO: 自定义passwordEncoder
 */
@Slf4j
public class CustomPasswordEncoder implements PasswordEncoder {

//    private static final String SALT = "SYW-XQL";

    private static final String PREFIX = "{";
    private static final String SUFFIX = "}";
    private final String idForEncode;
    private final PasswordEncoder passwordEncoderForEncode;
    private final Map<String, PasswordEncoder> idToPasswordEncoder;
    private PasswordEncoder defaultPasswordEncoderForMatches = new CustomPasswordEncoder.UnmappedIdPasswordEncoder();

    public CustomPasswordEncoder(String idForEncode, Map<String, PasswordEncoder> idToPasswordEncoder) {
        if (idForEncode == null) {
            throw new IllegalArgumentException("idForEncode cannot be null");
        } else if (!idToPasswordEncoder.containsKey(idForEncode)) {
            throw new IllegalArgumentException("idForEncode " + idForEncode + "is not found in idToPasswordEncoder " + idToPasswordEncoder);
        } else {
            Iterator var3 = idToPasswordEncoder.keySet().iterator();

            while(var3.hasNext()) {
                String id = (String)var3.next();
                if (id != null) {
                    if (id.contains("{")) {
                        throw new IllegalArgumentException("id " + id + " cannot contain " + "{");
                    }

                    if (id.contains("}")) {
                        throw new IllegalArgumentException("id " + id + " cannot contain " + "}");
                    }
                }
            }

            this.idForEncode = idForEncode;
            this.passwordEncoderForEncode = idToPasswordEncoder.get(idForEncode);
            this.idToPasswordEncoder = new HashMap(idToPasswordEncoder);
        }
    }

    public void setDefaultPasswordEncoderForMatches(PasswordEncoder defaultPasswordEncoderForMatches) {
        if (defaultPasswordEncoderForMatches == null) {
            throw new IllegalArgumentException("defaultPasswordEncoderForMatches cannot be null");
        } else {
            this.defaultPasswordEncoderForMatches = defaultPasswordEncoderForMatches;
        }
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return "{" + this.idForEncode + "}" + this.passwordEncoderForEncode.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String prefixEncodedPassword) {
        log.info("rawPassword:{}", rawPassword);
        log.info("prefixEncodedPassword:{}", prefixEncodedPassword);
        if (rawPassword == null && prefixEncodedPassword == null) {
            return true;
        } else {
            String id = this.extractId(prefixEncodedPassword);
            PasswordEncoder delegate = (PasswordEncoder)this.idToPasswordEncoder.get(id);
            if (delegate == null) {
                return this.defaultPasswordEncoderForMatches.matches(rawPassword, prefixEncodedPassword);
            } else {
                String encodedPassword = this.extractEncodedPassword(prefixEncodedPassword);
                // 私钥解密
                return delegate.matches(rawPassword, encodedPassword);
            }
        }
    }

    private String extractId(String prefixEncodedPassword) {
        if (prefixEncodedPassword == null) {
            return null;
        } else {
            int start = prefixEncodedPassword.indexOf("{");
            if (start != 0) {
                return null;
            } else {
                int end = prefixEncodedPassword.indexOf("}", start);
                return end < 0 ? null : prefixEncodedPassword.substring(start + 1, end);
            }
        }
    }

    @Override
    public boolean upgradeEncoding(String encodedPassword) {
        String id = this.extractId(encodedPassword);
        return !this.idForEncode.equalsIgnoreCase(id);
    }

    private String extractEncodedPassword(String prefixEncodedPassword) {
        int start = prefixEncodedPassword.indexOf("}");
        return prefixEncodedPassword.substring(start + 1);
    }

    private class UnmappedIdPasswordEncoder implements PasswordEncoder {
        private UnmappedIdPasswordEncoder() {
        }

        @Override
        public String encode(CharSequence rawPassword) {
            throw new UnsupportedOperationException("encode is not supported");
        }

        @Override
        public boolean matches(CharSequence rawPassword, String prefixEncodedPassword) {
            String id = CustomPasswordEncoder.this.extractId(prefixEncodedPassword);
            throw new IllegalArgumentException("There is no PasswordEncoder mapped for the id \"" + id + "\"");
        }
    }

}
