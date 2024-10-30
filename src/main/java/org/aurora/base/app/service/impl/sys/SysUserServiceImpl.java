package org.aurora.base.app.service.impl.sys;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.aurora.base.app.common.dict.SysParam;
import org.aurora.base.app.common.dict.UserType;
import org.aurora.base.app.common.dict.YesOrNo;
import org.aurora.base.app.entity.sys.SysUser;
import org.aurora.base.app.mapper.sys.SysParamMapper;
import org.aurora.base.app.mapper.sys.SysUserMapper;
import org.aurora.base.app.service.impl.BaseServiceImpl;
import org.aurora.base.app.service.sys.SysUserService;
import org.aurora.base.app.shiro.ShiroUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements SysUserService {
    @Autowired
    public SysUserServiceImpl(SysParamMapper paramMapper) {
        this.paramMapper = paramMapper;
    }

    private final SysParamMapper paramMapper;

    @Override
    public SysUser findByUsernameForLogin(String username) {
        var wrapper = new LambdaQueryWrapper<SysUser>()
                .select(
                        SysUser::getId,
                        SysUser::getPassword,
                        SysUser::getSalt,
                        SysUser::getStatus,
                        SysUser::getUserType)
                .eq(SysUser::getUsername, username);
        return getBaseMapper().selectOne(wrapper);
    }

    @Override
    @Transactional
    public boolean create(SysUser user) {
        String defaultPwd = paramMapper.findValueByCode(SysParam.SYS_DEFAULT_PASSWORD);
        user.setUserType(UserType.ADMIN);
        user.setIsDeleted(YesOrNo.NO);
        user.setPassword(defaultPwd);
        ShiroUtils.encryptPassword(user);
        return super.create(user);
    }

    @Override
    @Transactional
    public boolean update(SysUser user) {
        SysUser updateUser = getBaseMapper().selectById(user.getId());
        if (updateUser == null || !UserType.ADMIN.equals(updateUser.getUserType())) {
            throw new IllegalArgumentException();
        }
        user.setUsername(null);
        user.setPassword(null);
        user.setSalt(null);
        user.setUserType(null);
        return super.update(user);
    }
}
