package org.aurora.base.app.controller;

import org.aurora.base.app.common.Result;
import org.aurora.base.app.common.validation.Password;
import org.aurora.base.app.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    @Autowired
    public LoginController(ShiroUtils shiroUtils) {
        this.shiroUtils = shiroUtils;
    }

    private final ShiroUtils shiroUtils;

    /**
     * 密码登录
     */
    @PostMapping("/login")
    public Result<?> login(
            @RequestParam String username,
            @RequestParam @Password String password) {
        String token = shiroUtils.loginByPassword(username, password);
        return Result.success(token);
    }
}
