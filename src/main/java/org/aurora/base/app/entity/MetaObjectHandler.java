package org.aurora.base.app.entity;

import org.apache.ibatis.reflection.MetaObject;
import org.aurora.base.app.shiro.ShiroUtils;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * MyBatis-Plus 自动填充字段
 */
@Component
public class MetaObjectHandler implements com.baomidou.mybatisplus.core.handlers.MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        this.strictInsertFill(metaObject, "createUserId", Long.class, ShiroUtils.getCurrentUserId());
        this.strictInsertFill(metaObject, "version", Integer.class, 0);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "lastTime", LocalDateTime.class, LocalDateTime.now());
        this.strictUpdateFill(metaObject, "lastUserId", Long.class, ShiroUtils.getCurrentUserId());
    }
}
