package org.aurora.base.app.entity.sys;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aurora.base.app.entity.BaseEntity;

/**
 * 系统参数表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_system_param")
public class SysParam extends BaseEntity {

    private String paramCode;

    private String paramDesc;

    private String paramValue;
}
