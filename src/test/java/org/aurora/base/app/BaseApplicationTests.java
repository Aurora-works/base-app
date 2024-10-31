package org.aurora.base.app;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BaseApplicationTests {

    @Test
    void contextLoads() {
        System.out.println("Hello World");
    }

    @Test
    void randomString() {
        System.out.println(RandomStringUtils.randomAlphabetic(64));
    }
}
