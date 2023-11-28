package example.entity.request;//package org.example.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.service.validation_code.entity.StringCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/14 下午10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "登录实体")
public class LoginRequest {

    @JsonProperty("user_name")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @JsonProperty("user_password")
    @ApiModelProperty(value = "密码")
    private String userPassword;
    @JsonProperty("vc_picture_code")
    @ApiModelProperty(value = "图形验证码")
    private StringCode vcPictureCode;

    @JsonProperty("user_telephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;
    @JsonProperty("vc_telephone_code")
    @ApiModelProperty(value = "手机短信验证码")
    private StringCode vcTelephoneCode;

    @JsonProperty("user_sys_email")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;
    @JsonProperty("vc_email_code")
    @ApiModelProperty(value = "邮箱验证码")
    private StringCode vcEmailCode;
}
