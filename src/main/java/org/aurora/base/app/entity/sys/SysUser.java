package org.aurora.base.app.entity.sys;

import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.aurora.base.app.common.dict.YesOrNo;
import org.aurora.base.app.entity.BaseEntity;

/**
 * 系统用户表
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@TableName("t_system_user")
public class SysUser extends BaseEntity {

    private String username;

    @JsonIgnore
    private String password;

    @JsonIgnore
    private String salt;

    private String nickname;

    private String sex;

    private String email;

    private String mobile;

    private String avatar;

    private String status;

    private String userType;

    @TableLogic(
            value = YesOrNo.NO,
            delval = YesOrNo.YES
    )
    @JsonIgnore
    private String isDeleted;
}
