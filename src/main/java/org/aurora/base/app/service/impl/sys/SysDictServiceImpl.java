package org.aurora.base.app.service.impl.sys;

import org.aurora.base.app.entity.sys.SysDict;
import org.aurora.base.app.mapper.sys.SysDictMapper;
import org.aurora.base.app.service.impl.BaseServiceImpl;
import org.aurora.base.app.service.sys.SysDictService;
import org.springframework.stereotype.Service;

@Service
public class SysDictServiceImpl extends BaseServiceImpl<SysDictMapper, SysDict> implements SysDictService {
}
