package example.entity.response.father;

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
    private int code;

    @ApiModelProperty("响应信息")
    private String msg;


}
