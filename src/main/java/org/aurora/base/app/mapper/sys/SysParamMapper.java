package org.aurora.base.app.mapper.sys;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.aurora.base.app.entity.sys.SysParam;
import org.aurora.base.app.mapper.BaseMapper;

@Mapper
public interface SysParamMapper extends BaseMapper<SysParam> {

    @Select("select param_value from t_system_param where param_code = #{code}")
    String findValueByCode(String code);
}
