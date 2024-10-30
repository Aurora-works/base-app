package org.aurora.base.app.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.aurora.base.app.common.dict.Status;
import org.aurora.base.app.common.dto.AuthDTO;
import org.aurora.base.app.mapper.sys.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SysUserRealm extends AuthorizingRealm {

    @Lazy
    @Autowired
    private SysUserMapper userMapper;

    @Lazy
    @Autowired
    private ShiroUtils shiroUtils;

    /**
     * 获取授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        Long userId = (Long) principalCollection.getPrimaryPrincipal();
        List<AuthDTO> auths = userMapper.findAuthByUserId(userId);
        Set<String> roles = new HashSet<>();
        Set<String> permissions = new HashSet<>();
        for (AuthDTO auth : auths) {
            if (Status.ENABLED.equals(auth.getRoleStatus())) {
                roles.add(auth.getRoleCode());
                if (Status.ENABLED.equals(auth.getMenuStatus())) {
                    String menuCode = auth.getMenuCode();
                    if (Status.ENABLED.equals(auth.getCreateOp())) {
                        permissions.add(menuCode + ":create");
                    }
                    if (Status.ENABLED.equals(auth.getUpdateOp())) {
                        permissions.add(menuCode + ":update");
                    }
                    if (Status.ENABLED.equals(auth.getDeleteOp())) {
                        permissions.add(menuCode + ":delete");
                    }
                    if (Status.ENABLED.equals(auth.getReadOp())) {
                        permissions.add(menuCode + ":read");
                    }
                }
            }
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.setRoles(roles);
        authorizationInfo.setStringPermissions(permissions);
        return authorizationInfo;
    }

    /**
     * 获取身份验证信息
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String token = ((JWTToken) authenticationToken).getToken();
        String subject = JWTUtils.getSubject(token); // userId
        // 校验 token 是否正确
        if (subject == null || !JWTUtils.verify(token, subject)) {
            throw new IncorrectCredentialsException(token);
        }
        // 校验 token 是否过期
        if (shiroUtils.isTokenExpired(token, subject)) {
            throw new AuthenticationException(token);
        }
        // 刷新 token 生命周期
        shiroUtils.refreshToken(token, subject);
        return new SimpleAuthenticationInfo(
                Long.valueOf(subject), // userId
                token,
                getName()
        );
    }
}
