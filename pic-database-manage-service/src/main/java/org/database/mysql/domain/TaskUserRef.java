package org.database.mysql.domain;

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
    private Integer refId;
    private String refUserId;
    private String refTaskId;
    private long refAllReq;
    private long refSuccessReq;
    private long refStartTime;
    private long refEndTime;
    private long refTestTime;
    private int refState;
}
