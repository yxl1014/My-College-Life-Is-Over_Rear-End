package example.entity.response.father;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/30 上午2:04
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("响应")
public class Response {

    @ApiModelProperty("状态码")
    @JsonProperty("code")
    private int code;

    @ApiModelProperty("信息")
    @JsonProperty("msg")
    private String msg;
}
