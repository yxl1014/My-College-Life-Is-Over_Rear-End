package org.user.entity.request;//package org.example.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonProperty("userName")
    @ApiModelProperty(value = "用户名")
    private String userName;
    @JsonProperty("userPassword")
    @ApiModelProperty(value = "密码")
    private String userPassword;
    @JsonProperty("vcPictureCode")
    @ApiModelProperty(value = "图形验证码")
    private DigitOperaCodeRequest vcPictureCode;

    @JsonProperty("userTelephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;
    @JsonProperty("vcTelephoneCode")
    @ApiModelProperty(value = "手机短信验证码")
    private StringCodeRequest vcTelephoneCode;

    @JsonProperty("userSysEmail")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;
    @JsonProperty("vcEmailCode")
    @ApiModelProperty(value = "邮箱验证码")
    private StringCodeRequest vcEmailCode;
}
