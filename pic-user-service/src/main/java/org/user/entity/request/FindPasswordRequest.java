package org.user.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yxl17
 * @Package : org.user.entity.request
 * @Create on : 2024/1/8 20:13
 **/

@Data
@ApiModel("找回密码验证")
public class FindPasswordRequest {
    /**
     * 找回密码的Uuid
     */
    @ApiModelProperty("找回密码的Uuid")
    @JsonProperty("pwdUuid")
    private String pwdUuid;

    /**
     * 验证码的uuid
     */
    @ApiModelProperty("验证码的Uuid")
    @JsonProperty("uuid")
    private String uuid;
    /**
     * 验证码
     */
    @ApiModelProperty("验证码")
    @JsonProperty("code")
    private String code;

    /**
     * 密保答案1
     */
    @ApiModelProperty("密保答案1")
    @JsonProperty("answerOne")
    private String answerOne;

    /**
     * 密保答案2
     */
    @ApiModelProperty("密保答案2")
    @JsonProperty("answerTwo")
    private String answerTwo;

    /**
     * 验证类型
     */
    @ApiModelProperty("验证类型：1、密保，2、手机，3、邮箱")
    @JsonProperty("type")
    private int type;
}
