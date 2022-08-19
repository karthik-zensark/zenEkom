package com.zensark.springdemo.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.security.InvalidParameterException;

public class PasswordUtil {
    private final PasswordEncoder encoder;

    public PasswordUtil() {
        encoder = new BCryptPasswordEncoder();
    }

    public String encrypt(String plain) {
        return encoder.encode(plain);
    }

    public boolean compare(String encrypted, String plain) {
        if (StringUtils.hasLength(encrypted) && StringUtils.hasLength(plain)) {
            return encoder.matches(plain, encrypted);
        } else {
            throw new InvalidParameterException("encrypted or plain can't be null");
        }
    }
}
