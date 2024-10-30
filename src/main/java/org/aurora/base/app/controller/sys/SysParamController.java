package org.aurora.base.app.controller.sys;

import org.aurora.base.app.controller.BaseController;
import org.aurora.base.app.entity.sys.SysParam;
import org.aurora.base.app.service.BaseService;
import org.aurora.base.app.service.sys.SysParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/param")
public class SysParamController extends BaseController<SysParam> {
    @Autowired
    public SysParamController(SysParamService paramService) {
        this.paramService = paramService;
    }

    private final SysParamService paramService;

    @Override
    protected String menuCode() {
        return "sys_param";
    }

    @Override
    protected BaseService<SysParam> service() {
        return paramService;
    }
}
