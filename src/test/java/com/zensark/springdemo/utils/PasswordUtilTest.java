package com.zensark.springdemo.utils;

import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class PasswordUtilTest {
    private PasswordUtil util = new PasswordUtil();

    @Test
    void encrypt() {
        String encrypted = util.encrypt("mark@123");
        assertNotNull(encrypted);
        assertNotEquals("mark@123", encrypted);
    }

    @Test
    void compare() {
        String encrypted = "$2a$10$n/rxu9mv3cCeMkW7Pl/EveS.CxVBduShdI.j2T7YC4t4mQ0PdlZPq";
        String plain = "test@123";
        boolean compareResult = util.compare(encrypted, plain);
        assertEquals(true, compareResult);
    }

    @Test
    void compareShouldThrowWhenEncryptedIsNull() {
        assertThrows(InvalidParameterException.class, () -> {
            util.compare(null, "plain");
        });
    }

    @Test
    void compareShouldThrowWhenPlainIsNull() {
        assertThrows(InvalidParameterException.class, () -> {
            util.compare("encrypted", null);
        });
    }
}