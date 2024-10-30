package org.aurora.base.app.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.ArrayUtils;
import org.aurora.base.app.common.view.FilterRule;
import org.aurora.base.app.common.view.PageVO;
import org.aurora.base.app.entity.BaseEntity;
import org.aurora.base.app.mapper.BaseMapper;
import org.aurora.base.app.service.BaseService;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class BaseServiceImpl<M extends BaseMapper<T>, T extends BaseEntity> extends ServiceImpl<M, T> implements BaseService<T> {
    @SuppressWarnings("unchecked")
    public BaseServiceImpl() {
        var entityClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        var superClass = entityClass.getSuperclass();
        entityFields = ArrayUtils.addAll(entityClass.getDeclaredFields(), superClass.getDeclaredFields());
    }

    private final Field[] entityFields;

    @Override
    public PageVO<T> findAllInPage(long page, long size, String sort, String order, List<FilterRule> rules) {

        checkAndSetFields(sort, order, rules);

        Page<T> p = Page.of(page, size);
        if ("asc".equals(order)) {
            p.addOrder(OrderItem.asc(sort));
        } else {
            p.addOrder(OrderItem.desc(sort));
        }

        var wrapper = new QueryWrapper<T>();
        if (rules != null) {
            for (FilterRule rule : rules) {
                switch (rule.getOp().toLowerCase()) {
                    case FilterRule.EQ -> wrapper.eq(rule.getField(), rule.getValue());
                    case FilterRule.NE -> wrapper.ne(rule.getField(), rule.getValue());
                    case FilterRule.GT -> wrapper.gt(rule.getField(), rule.getValue());
                    case FilterRule.GE -> wrapper.ge(rule.getField(), rule.getValue());
                    case FilterRule.LT -> wrapper.lt(rule.getField(), rule.getValue());
                    case FilterRule.LE -> wrapper.le(rule.getField(), rule.getValue());
                    default -> wrapper.like(rule.getField(), rule.getValue());
                }
            }
        }
        return PageVO.of(getBaseMapper().selectPage(p, wrapper));
    }

    @Override
    @Transactional
    public boolean create(T entity) {
        return getBaseMapper().insert(entity) > 0;
    }

    @Override
    @Transactional
    public boolean update(T entity) {
        return getBaseMapper().updateById(entity) > 0;
    }

    /**
     * 校验过滤条件中所有字段名称的合法性
     */
    protected void checkAndSetFields(String sort, String order, List<FilterRule> rules) {

        // sort
        boolean flag = true;
        for (Field field : entityFields) {
            if (field.getName().equals(sort)) {
                flag = false;
                break;
            }
        }

        // order
        if (flag || !"asc".equals(order) && !"desc".equals(order)) {
            throw new IllegalArgumentException();
        }

        // filterRules
        if (rules != null) {
            outer:
            for (FilterRule rule : rules) {
                for (Field field : entityFields) {
                    if (field.getName().equals(rule.getField())) {
                        // 驼峰转下划线
                        String result = com.baomidou.mybatisplus.core.toolkit.StringUtils.camelToUnderline(rule.getField());
                        // like
                        if (FilterRule.LIKE.equals(rule.getOp())) {
                            result = String.format("UPPER(%s)", result);
                            rule.setValue(rule.getValue().toString().toUpperCase());
                        }
                        rule.setField(result);
                        continue outer;
                    }
                }
                throw new IllegalArgumentException();
            }
        }
    }
}
