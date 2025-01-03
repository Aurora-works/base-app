package org.aurora.base.app.shiro;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest
public class JWTUtilsTest {

    @Autowired
    private JWTUtils jwtUtils;

    private static final String subject = "1";

    private static String token = null;

    @Test
    @Order(1)
    void testSign() {
        token = jwtUtils.sign(subject);
        System.out.println(token);
    }

    @Test
    @Order(2)
    void testVerify() {
        boolean verify = jwtUtils.verify(token, subject);
        System.out.println(verify);
    }

    @Test
    @Order(3)
    void testGetSubject() {
        String subject = JWTUtils.getSubject(token);
        System.out.println(subject);
    }
}
