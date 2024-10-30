package org.aurora.base.app.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aurora.base.app.entity.BaseEntity;

/**
 * 系统角色对系统功能菜单的操作权限表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_system_role_menu")
public class SysRoleMenu extends BaseEntity {

    private String createOp;

    private String updateOp;

    private String deleteOp;

    private String readOp;

    private Long roleId;

    private Long menuId;
}
