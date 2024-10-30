package org.aurora.base.app.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseEntity {

    private Long id;

    private String description;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUserId;

    @TableField(fill = FieldFill.UPDATE)
    private LocalDateTime lastTime;

    @TableField(fill = FieldFill.UPDATE)
    private Long lastUserId;

    @Version
    @TableField(fill = FieldFill.INSERT)
    private Integer version;
}
