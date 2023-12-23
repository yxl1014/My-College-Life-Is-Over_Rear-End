package org.user.entity.request.findPasswd;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/3 下午7:19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "密保查询请求")
public class SecProblemRequest {
    @JsonProperty("flag")
    @ApiModelProperty(value = "flag 1.用户名  2.手机号码 3.邮箱")
    private short flag;

    @JsonProperty("userName")
    @ApiModelProperty(value = "用户名")
    private String userName;

    @JsonProperty("userTelephone")
    @ApiModelProperty(value = "电话")
    private String userTelephone;

    @JsonProperty("userSysEmail")
    @ApiModelProperty(value = "邮箱")
    private String userSysEmail;

}
