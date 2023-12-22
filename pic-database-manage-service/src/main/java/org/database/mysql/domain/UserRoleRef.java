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
 * @Create on : 2023/12/17 16:10
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user_role_ref")
public class UserRoleRef {
   @TableId(type = IdType.AUTO)
    private Integer refId;
    private String refUserId;
    private Short refRoleId;
}
