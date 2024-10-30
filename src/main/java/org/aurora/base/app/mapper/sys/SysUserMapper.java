package org.aurora.base.app.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.aurora.base.app.common.dto.AuthDTO;
import org.aurora.base.app.entity.sys.SysUser;
import org.aurora.base.app.mapper.BaseMapper;

import java.util.List;

@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {

    List<AuthDTO> findAuthByUserId(@Param("userId") Long userId);
}
