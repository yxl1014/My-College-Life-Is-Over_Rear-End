package org.mysql.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl17
 * @Package : org.mysql.domain
 * @Create on : 2023/12/17 14:12
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RolePowerRef {
    private Short refRoleId;
    private Integer refPowerId;
}
