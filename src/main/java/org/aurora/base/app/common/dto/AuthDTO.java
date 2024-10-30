package org.aurora.base.app.common.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString
@EqualsAndHashCode
public class AuthDTO {

    private String roleCode;

    private String roleStatus;

    private String menuCode;

    private String menuStatus;

    private String createOp;

    private String updateOp;

    private String deleteOp;

    private String readOp;
}
