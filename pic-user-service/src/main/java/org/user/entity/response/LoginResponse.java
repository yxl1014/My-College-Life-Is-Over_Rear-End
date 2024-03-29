package org.user.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yxl17
 * @Package : org.user.entity.response
 * @Create on : 2024/1/3 20:48
 **/

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("登录响应")
public class LoginResponse {

    @ApiModelProperty("token")
    @JsonProperty("token")
    private String token;

    @ApiModelProperty("userId")
    @JsonProperty("userId")
    private String userId;
}
