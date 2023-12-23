package org.user.entity.request;


import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CRUDRequest {
    private  String userId;

    @JsonProperty("user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @JsonProperty("user_password")
    @ApiModelProperty(value = "密码")
    private String userPassword;

    @JsonProperty("userTelephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;

    @JsonProperty("user_id_card")
    @ApiModelProperty(value = "身份证卡")
    private String userIdCard;

    @JsonProperty("userSysEmail")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;

    @JsonProperty("userNickName")
    @ApiModelProperty(value = "昵称")
    private String userNickName;

    @JsonProperty("userGender")
    @ApiModelProperty(value = "性别")
    private String userGender;

    @JsonProperty("userBornDay")
    @ApiModelProperty(value = "出生日期格式:2023-09-10")
    private String userBornDay;

    @JsonProperty("userSecProblem1")
    @ApiModelProperty(value = "密保1")
    private String userSecProblem1;
    @JsonProperty("userSecAnswer1")
    @ApiModelProperty(value = "密保答案1")
    private String userSecAnswer1;
    @JsonProperty("userSecProblem2")
    @ApiModelProperty(value = "密保2")
    private String userSecProblem2;
    @JsonProperty("userSecAnswer2")
    @ApiModelProperty(value = "密保答案2")
    private String userSecAnswer2;
    @JsonProperty("userSecProblem3")
    @ApiModelProperty(value = "密保3")
    private String userSecProblem3;
    @JsonProperty("userSecAnswer3")
    @ApiModelProperty(value = "密保答案3")
    private String userSecAnswer3;

    @JsonProperty("userCompany")
    @ApiModelProperty(value = "工作单位")
    private String userCompany;

    @JsonProperty("userHome")
    @ApiModelProperty(value = "家庭住址")
    private String userHome;

    @JsonProperty("userPersonalProfile")
    @ApiModelProperty(value = "用户个人简介")
    private String userPersonalProfile;

}
