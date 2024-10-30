package org.aurora.base.app.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aurora.base.app.entity.BaseEntity;

/**
 * 系统功能菜单表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_system_menu")
public class SysMenu extends BaseEntity {

    private String menuCode;

    private String menuName;

    private String href;

    private Long parentId;

    private String orderBy;

    private String css;

    private String isOpen;

    private String status;
}
