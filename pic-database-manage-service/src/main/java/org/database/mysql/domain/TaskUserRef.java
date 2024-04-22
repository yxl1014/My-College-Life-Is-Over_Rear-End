package org.database.mysql.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @Package : org.database.mysql.domain
 * @Create on : 2024/3/29 上午10:37
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_task_user_ref")
public class TaskUserRef {
    @TableId(type = IdType.AUTO)
    private Integer refId;
    private String refUserId;
    private String refTaskId;
    private Long refAllReq;
    private Long refSuccessReq;
    private Long refStartTime;
    private Long refEndTime;
    private Long refTestTime;
    private Integer refState;
}
