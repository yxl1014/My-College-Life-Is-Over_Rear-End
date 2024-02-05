package org.user.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author yxl17
 * @Package : org.user.entity.request
 * @Create on : 2024/1/8 20:34
 **/


@Data
@ApiModel("找回密码修改密码")
public class FindUpdatePwdRequest {
    /**
     * 找回密码的Uuid
     */
    @ApiModelProperty("找回密码的Uuid")
    @JsonProperty("uuid")
    private String uuid;

    /**
     * 新密码
     */
    @ApiModelProperty("新密码")
    @JsonProperty("password")
    private String password;
}
