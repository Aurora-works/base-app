package org.aurora.base.app.controller.sys;

import org.aurora.base.app.controller.BaseController;
import org.aurora.base.app.entity.sys.SysUser;
import org.aurora.base.app.service.BaseService;
import org.aurora.base.app.service.sys.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/user")
public class SysUserController extends BaseController<SysUser> {
    @Autowired
    public SysUserController(SysUserService userService) {
        this.userService = userService;
    }

    private final SysUserService userService;

    @Override
    protected String menuCode() {
        return "sys_user";
    }

    @Override
    protected BaseService<SysUser> service() {
        return userService;
    }
}
