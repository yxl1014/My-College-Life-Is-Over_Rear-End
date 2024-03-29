package org.user.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用于数字相加的验证码实体类
 * @author: HammerRay
 * @date: 2023/11/18 下午5:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("验证码响应")
public class VerityCodeResponse {
    /**
     * 验证码的uuid
     */
    @ApiModelProperty("验证码的Uuid")
    @JsonProperty("uuid")
    private String uuid;

    /**
     * operationFormula＋上图片 之和的base64编码
     */
    @ApiModelProperty("base64编码的图片的字符串")
    @JsonProperty("base64Img")
    private String base64Img;

    /**
     * 电话验证码没开通，所以把验证码一起发回去
     */
    @ApiModelProperty("电话验证码没开通，所以把验证码一起发回去")
    @JsonProperty("code")
    private String code;
}
