package org.aurora.base.app.service.impl.sys;

import org.aurora.base.app.entity.sys.SysUser;
import org.aurora.base.app.service.sys.SysUserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SysUserServiceImplTest {

    @Autowired
    private SysUserService userService;

    @Test
    void findByUsernameForLogin() {
        SysUser user = userService.findByUsernameForLogin("admin");
        System.out.println(user);
    }
}
