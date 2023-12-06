package example.entity.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/30 下午11:51
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("数字验证码请求")
public class DigitOperaCodeRequest {
    /**
     * 验证码的uuid
     */
    @ApiModelProperty("验证码的Uuid")
    @JsonProperty("vcId")
    private String vcId;
    /**
     * 运算的结果
     */
    @ApiModelProperty("用户输入的验证码的计算的结果")
    @JsonProperty("result")
    private int result;

    public boolean isEmpty() {
        return vcId.isEmpty();
    }
}
