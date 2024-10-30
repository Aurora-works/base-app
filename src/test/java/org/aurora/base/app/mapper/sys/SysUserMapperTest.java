package org.aurora.base.app.mapper.sys;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysUserMapperTest {

    @Autowired
    private SysUserMapper userMapper;

    @Test
    void findAuthByUserId() {
        userMapper.findAuthByUserId(1L).forEach(System.out::println);
    }
}
