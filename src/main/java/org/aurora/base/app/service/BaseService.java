package org.aurora.base.app.service;

import org.aurora.base.app.common.view.FilterRule;
import org.aurora.base.app.common.view.PageVO;
import org.aurora.base.app.entity.BaseEntity;

import java.util.List;

// mybatis-plus v3.5.9 不再建议使用 IService 避免业务层数据混乱
// public interface BaseService<T extends BaseEntity> extends IService<T> {
public interface BaseService<T extends BaseEntity> {

    PageVO<T> findAllInPage(long page, long size, String sort, String order, List<FilterRule> rules);

    boolean create(T entity);

    boolean update(T entity);
}
