package org.aurora.base.app.service.sys;

import org.aurora.base.app.entity.sys.SysUser;
import org.aurora.base.app.service.BaseService;

public interface SysUserService extends BaseService<SysUser> {

    SysUser findByUsernameForLogin(String username);
}
