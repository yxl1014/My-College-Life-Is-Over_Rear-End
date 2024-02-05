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
 * @date: 2023/11/27 下午9:14
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("UUID响应")
public class UuidResponse{

    @ApiModelProperty("UUID")
    @JsonProperty("uuid")
    private String uuid;
}
