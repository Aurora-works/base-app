package org.aurora.base.app.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aurora.base.app.entity.BaseEntity;

/**
 * 系统数据字典表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_system_dict")
public class SysDict extends BaseEntity {

    private String dictCode;

    private String dictKey;

    private String dictValue;

    private String orderBy;

    private String status;
}
