package org.aurora.base.app.controller.sys;

import org.aurora.base.app.controller.BaseController;
import org.aurora.base.app.entity.sys.SysDict;
import org.aurora.base.app.service.BaseService;
import org.aurora.base.app.service.sys.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys/dict")
public class SysDictController extends BaseController<SysDict> {
    @Autowired
    public SysDictController(SysDictService dictService) {
        this.dictService = dictService;
    }

    private final SysDictService dictService;

    @Override
    protected String menuCode() {
        return "sys_dict";
    }

    @Override
    protected BaseService<SysDict> service() {
        return dictService;
    }
}
