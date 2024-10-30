package org.aurora.base.app.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import org.apache.commons.lang3.StringUtils;
import org.aurora.base.app.common.Result;
import org.aurora.base.app.common.view.FilterRule;
import org.aurora.base.app.common.view.PageVO;
import org.aurora.base.app.entity.BaseEntity;
import org.aurora.base.app.service.BaseService;
import org.aurora.base.app.shiro.ShiroUtils;
import org.aurora.base.app.util.JSONUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public abstract class BaseController<T extends BaseEntity> {

    protected abstract String menuCode();

    protected abstract BaseService<T> service();

    /**
     * 新增
     */
    @PostMapping("/create")
    public Result<?> create(T entity) {
        ShiroUtils.checkPermission(menuCode() + ":create");
        if (entity.getId() != null) {
            throw new IllegalArgumentException();
        }
        if (service().create(entity)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    public Result<?> update(T entity) {
        ShiroUtils.checkPermission(menuCode() + ":update");
        if (entity.getId() == null || entity.getVersion() == null) {
            throw new IllegalArgumentException();
        }
        if (service().update(entity)) {
            return Result.success();
        }
        return Result.fail();
    }

    /**
     * 数据列表
     */
    @PostMapping("/list")
    public Result<PageVO<T>> list(
            @RequestParam(required = false, defaultValue = "1") long page,
            @RequestParam(required = false, defaultValue = "50") long size,
            @RequestParam(required = false, defaultValue = "id") String sort,
            @RequestParam(required = false, defaultValue = "asc") String order,
            @RequestParam(required = false, defaultValue = "[]") String filterRules) {
        ShiroUtils.checkPermission(menuCode() + ":read");
        List<FilterRule> rules = parseFilterRules(filterRules);
        return Result.success(service().findAllInPage(page, size, sort, order, rules));
    }

    /**
     * 将 JSON 格式的过滤条件转换为 Java 对象格式
     */
    protected List<FilterRule> parseFilterRules(String filterRules) {
        if (StringUtils.isBlank(filterRules) || "[]".equals(filterRules)) {
            return null;
        }
        return JSONUtils.readValue(filterRules, new TypeReference<>() {
        });
    }
}
