package example.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/27 下午9:29
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SecProblemResponse extends UuidResponse{
    @JsonProperty("userSecProblem1")
    @ApiModelProperty(value = "密保1")
    private String userSecProblem1;
    @JsonProperty("userSecAnswer1")
    @ApiModelProperty(value = "密保答案1")
    private String userSecAnswer1;
    @JsonProperty("userSecProblem2")
    @ApiModelProperty(value = "密保2")
    private String userSecProblem2;
    @JsonProperty("userSecAnswer2")
    @ApiModelProperty(value = "密保答案2")
    private String userSecAnswer2;
    @JsonProperty("userSecProblem3")
    @ApiModelProperty(value = "密保3")
    private String userSecProblem3;
    @JsonProperty("userSecAnswer3")
    @ApiModelProperty(value = "密保答案3")
    private String userSecAnswer3;
}
