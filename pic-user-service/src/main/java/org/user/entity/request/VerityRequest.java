package org.user.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yxl17
 * @Package : org.user.entity.request
 * @Create on : 2023/12/25 23:12
 **/

@Data
@ApiModel("请求验证")
public class VerityRequest {
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
}
