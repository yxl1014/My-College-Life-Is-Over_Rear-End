package org.commons.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Administrator
 * @Package : org.commons.domain
 * @Create on : 2024/5/8 下午8:46
 **/

// 测试结果类
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskTestMsg {
    private Long testTime;//测试时间
    private Integer testState; // 测试结果 0：成功  1：请求成功  2：预计结果不同
    private Long testCostTime; // 测试响应时间
    private String testError;  // 测试错误信息
}
