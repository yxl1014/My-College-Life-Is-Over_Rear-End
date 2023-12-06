package example.entity.request;//package org.example.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.entity.response.StringCodeResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/14 下午11:11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "注册请求实体")
public class RegisterRequest {

    @JsonProperty("userName")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @JsonProperty("userPassword")
    @ApiModelProperty(value = "密码")
    private String userPassword;

    @JsonProperty("userTelephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;
    @JsonProperty("vcTelephoneCode")
    @ApiModelProperty(value = "手机短信验证码")
    private StringCodeRequest  vcTelephoneCode;

    @JsonProperty("userSysEmail")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;
    @JsonProperty("vcEmailCode")
    @ApiModelProperty(value = "邮箱验证码")
    private StringCodeRequest  vcEmailCode;


    @JsonProperty("userNickName")
    @ApiModelProperty(value = "昵称")
    private String userNickName;
    @JsonProperty("userGender")
    @ApiModelProperty(value = "性别")
    private String userGender;
    @JsonProperty("userBornDay")
    @ApiModelProperty(value = "出生日期格式:2023-09-10")
    private String userBornDay;

    @JsonProperty("userIdCard")
    @ApiModelProperty(value = "身份证卡")
    private String userIdCard;
    @JsonProperty("userCompany")
    @ApiModelProperty(value = "工作单位")
    private String userCompany;
    @JsonProperty("userHome")
    @ApiModelProperty(value = "家庭住址")
    private String userHome;
    @JsonProperty("userPersonalProfile")
    @ApiModelProperty(value = "用户个人简介")
    private String userPersonalProfile;


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

}
