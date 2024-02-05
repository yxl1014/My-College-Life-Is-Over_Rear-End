package org.database.mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
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
@TableName("sys_role_power_ref")
public class RolePowerRef {
    @TableId(type = IdType.AUTO)

    private Integer refId;
    private Short refRoleId;
    private Integer refPowerId;
}
