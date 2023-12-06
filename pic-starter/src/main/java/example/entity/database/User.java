package example.entity.database;

/**
 * @description:
 * @author: HammerRay
 * @date:2023/11/4
 */
import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "user实体")
@Table(name = "sys_user")
public class User {
    @Id
    @JsonProperty("user_id")
    @ApiModelProperty(value = "用户uuid auto")
    private String userId;
    @JsonProperty("user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @JsonProperty("user_telephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;
    @JsonProperty("user_sys_email")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;
    @JsonProperty("user_password")
    @ApiModelProperty(value = "密码")
    private String userPassword;
    @JsonProperty("user_nick_name")
    @ApiModelProperty(value = "昵称")
    private String userNickName;
    @JsonProperty("user_gender")
    @ApiModelProperty(value = "性别")
    private String userGender;
    @JsonProperty("user_born_day")
    @ApiModelProperty(value = "出生日期")
    private Date userBornDay;

    @JsonProperty("user_id_card")
    @ApiModelProperty(value = "银行卡")
    private String userIdCard;
    @JsonProperty("user_money")
    @ApiModelProperty(value = "账户余额 auto")
    private Double userMoney;
    @JsonProperty("user_company")
    @ApiModelProperty(value = "工作单位")
    private String userCompany;
    @JsonProperty("user_home")
    @ApiModelProperty(value = "家庭住址")
    private String userHome;

    @JsonProperty("user_ip")
    @ApiModelProperty(value = "用户的ip地址")
    private String userIp;
    @JsonProperty("user_flag")
    @ApiModelProperty(value = "用户状态标记 auto")
    private int userFlag;
    @JsonProperty("user_personal_profile")
    @ApiModelProperty(value = "用户个人简介")
    private String userPersonalProfile;
    @JsonProperty("user_create_time")
    @ApiModelProperty(value = "账号创建时间 auto")
    private Timestamp userCreateTime;

    @JsonProperty("user_sec_problem_1")
    @ApiModelProperty(value = "密保1")
    private String userSecProblem1;
    @JsonProperty("user_sec_answer_1")
    @ApiModelProperty(value = "密保答案1")
    private String userSecAnswer1;
    @JsonProperty("user_sec_problem_2")
    @ApiModelProperty(value = "密保2")
    private String userSecProblem2;
    @JsonProperty("user_sec_answer_2")
    @ApiModelProperty(value = "密保答案2")
    private String userSecAnswer2;
    @JsonProperty("user_sec_problem_3")
    @ApiModelProperty(value = "密保3")
    private String userSecProblem3;
    @JsonProperty("user_sec_answer_3")
    @ApiModelProperty(value = "密保答案3")
    private String userSecAnswer3;
}

