package org.aurora.base.app.shiro;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class JWTUtilsTest {

    private static final String subject = "1";

    private static String token = null;

    @Test
    @Order(1)
    void testSign() {
        token = JWTUtils.sign(subject);
        System.out.println(token);
    }

    @Test
    @Order(2)
    void testVerify() {
        boolean verify = JWTUtils.verify(token, subject);
        System.out.println(verify);
    }

    @Test
    @Order(3)
    void testGetSubject() {
        String subject = JWTUtils.getSubject(token);
        System.out.println(subject);
    }
}
