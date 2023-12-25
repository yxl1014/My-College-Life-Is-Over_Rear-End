package org.user.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/27 下午9:29
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecProblemResponse{
    @JsonProperty("userSecProblemOne")
    @ApiModelProperty(value = "密保1")
    private String userSecProblemOne;
    @JsonProperty("userSecProblemTwo")
    @ApiModelProperty(value = "密保2")
    private String userSecProblemTwo;
}
