package org.user.entity.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.user.entity.response.father.Response;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/27 下午10:22
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("字符串类型的验证码响应")
public class StringCodeResponse extends Response {

    @ApiModelProperty("验证码的uuid")
    @JsonProperty("vcId")
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
