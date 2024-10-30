package org.aurora.base.app.service.impl.sys;

import org.aurora.base.app.entity.sys.SysParam;
import org.aurora.base.app.mapper.sys.SysParamMapper;
import org.aurora.base.app.service.impl.BaseServiceImpl;
import org.aurora.base.app.service.sys.SysParamService;
import org.springframework.stereotype.Service;

@Service
public class SysParamServiceImpl extends BaseServiceImpl<SysParamMapper, SysParam> implements SysParamService {
}
