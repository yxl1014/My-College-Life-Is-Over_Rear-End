package example.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.entity.response.father.Response;
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
public class SecProblemResponse extends Response {
    @JsonProperty("userSecProblem1")
    @ApiModelProperty(value = "密保1")
    private String userSecProblem1;
    @JsonProperty("userSecProblem2")
    @ApiModelProperty(value = "密保2")
    private String userSecProblem2;
    @JsonProperty("userSecProblem3")
    @ApiModelProperty(value = "密保3")
    private String userSecProblem3;

}
