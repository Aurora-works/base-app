package org.aurora.base.app.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aurora.base.app.entity.BaseEntity;

/**
 * 系统角色表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_system_role")
public class SysRole extends BaseEntity {

    private String roleCode;

    private String roleName;

    private Long parentId;

    private String orderBy;
}
