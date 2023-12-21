package org.database.mysql.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * @author yxl17
 * @Package : org.mysql.domain
 * @Create on : 2023/12/17 14:24
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("sys_user")
public class User {
    @TableId
    private String userId;
    private String userName;
    private String userTelephone;
    private String userSysEmail;
    private String userPassword;
    private String userNickName;
    private String userGender;
    private Timestamp userBornDay;
    private String userIdCard;
    private Double userMoney;
    private String userCompany;
    private String userHome;
    private String userIp;
    private Boolean userFlag;
    private String userPersonalProfile;
    private Timestamp userCreateTime;
    private String userSecProblemOne;
    private String userSecAnswerOne;
    private String userSecProblemTwo;
    private String userSecAnswerTwo;
    private String userSecProblemThree;
    private String userSecAnswerThree;
}
