package org.user.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/3 下午10:47
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("查看是否存在响应")
public class CheckExistResponse {

    @JsonProperty("uuid")
    @ApiModelProperty(value = "uuid")
    private String uuid;
    @JsonProperty("userId")
    @ApiModelProperty(value = "用户ID")
    private String userId;
    @JsonProperty("problemOne")
    @ApiModelProperty(value = "密保问题一")
    private String problemOne;
    @JsonProperty("problemTwo")
    @ApiModelProperty(value = "密保问题二")
    private String problemTwo;
    @JsonProperty("userTel")
    @ApiModelProperty(value = "电话")
    private String userTel;
    @JsonProperty("userEmail")
    @ApiModelProperty(value = "邮箱")
    private String userEmail;

}
