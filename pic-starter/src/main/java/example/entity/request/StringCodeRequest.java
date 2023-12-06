package example.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/12/1 上午12:08
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("字符串类型的验证码请求")
public class StringCodeRequest {

    @ApiModelProperty("验证码的uuid")
    @JsonProperty("vcId")
    private String vcId;

    @ApiModelProperty("验证码")
    @JsonProperty("validation")
    private String validation;

    public boolean isEmpty(){
        return this.validation.isEmpty();
    }
}