package org.aurora.base.app.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.lang.util.ByteSource;
import org.aurora.base.app.common.CommonConstant;
import org.aurora.base.app.common.dict.Status;
import org.aurora.base.app.common.dict.TodoUser;
import org.aurora.base.app.common.dict.UserType;
import org.aurora.base.app.entity.sys.SysUser;
import org.aurora.base.app.service.sys.SysUserService;
import org.aurora.base.app.util.RedisUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ShiroUtils {
    @Autowired
    public ShiroUtils(RedisUtils redisUtils, SysUserService userService) {
        this.redisUtils = redisUtils;
        this.userService = userService;
    }

    private final RedisUtils redisUtils;
    private final SysUserService userService;

    private static final RandomNumberGenerator saltGenerator = new SecureRandomNumberGenerator();

    /**
     * 校验 token 是否过期
     */
    public boolean isTokenExpired(String token, String subject) {
        return !redisUtils.exists(tokenRedisKey(token, subject));
    }

    /**
     * 刷新 token 生命周期
     */
    public void refreshToken(String token, String subject) {
        redisUtils.expire(
                tokenRedisKey(token, subject),
                CommonConstant.TOKEN_EXPIRE_TIME.toMillis(),
                TimeUnit.MILLISECONDS);
    }

    /**
     * 返回 token 在 redis 中的 key
     */
    public String tokenRedisKey(String token, String subject) {
        return CommonConstant.TOKEN_REDIS_PREFIX + ":" + subject + ":" + token;
    }

    /**
     * 密码登录
     */
    public String loginByPassword(String username, String password) {
        SysUser user = userService.findByUsernameForLogin(username);
        if (user == null || !UserType.ADMIN.equals(user.getUserType())) {
            throw new UnknownAccountException();
        }
        if (!Status.ENABLED.equals(user.getStatus())) {
            throw new LockedAccountException();
        }
        if (!generatePassword(password, user.getSalt()).equals(user.getPassword())) {
            throw new IncorrectCredentialsException();
        }
        String token = JWTUtils.sign(user.getId().toString());
        redisUtils.set(
                tokenRedisKey(token, user.getId().toString()),
                null,
                CommonConstant.TOKEN_EXPIRE_TIME.toMillis(),
                TimeUnit.MILLISECONDS);
        return token;
    }

    /**
     * 密码加密
     */
    public static void encryptPassword(SysUser user) {
        user.setSalt(saltGenerator.nextBytes().toHex());
        user.setPassword(generatePassword(user.getPassword(), user.getSalt()));
    }

    /**
     * 生成密码
     */
    public static String generatePassword(String password, String salt) {
        return new SimpleHash(
                CommonConstant.SHIRO_HASH_ALGORITHM_NAME,
                password,
                ByteSource.Util.bytes(salt),
                CommonConstant.SHIRO_HASH_ITERATIONS
        ).toHex();
    }

    /**
     * 获取当前用户id
     */
    public static Long getCurrentUserId() {
        Long userId = (Long) SecurityUtils.getSubject().getPrincipal();
        if (userId == null) {
            return TodoUser.USER_NO_LOGIN;
        }
        return userId;
    }

    /**
     * 检查权限 (断言)
     */
    public static void checkPermission(String permission) {
        SecurityUtils.getSubject().checkPermission(permission);
    }

    /**
     * 检查权限
     */
    public static boolean isPermitted(String permission) {
        return SecurityUtils.getSubject().isPermitted(permission);
    }
}