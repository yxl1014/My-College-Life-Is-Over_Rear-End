package org.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl17
 * @Package : org.mysql.domain
 * @Create on : 2023/12/17 14:10
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {
    private Short roleId;
    private String roleName;
    private Timestamp roleCreateTime;
    private Boolean roleStatusFlag;
    private String roleRemark;
}
