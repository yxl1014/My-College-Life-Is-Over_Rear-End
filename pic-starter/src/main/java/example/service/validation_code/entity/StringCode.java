package example.service.validation_code.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/27 下午10:22
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("字符串类型的验证码")
public class StringCode {
    @ApiModelProperty("验证码的uuid")
    @JsonProperty("vc_id")
    private String vcId;

    @ApiModelProperty("验证码")
    @JsonProperty("validation")
    private String validation;

    public boolean isEmpty(){
        if(this.vcId.isEmpty()) {
            return true;
        }
        return this.validation.isEmpty();
    }
}
