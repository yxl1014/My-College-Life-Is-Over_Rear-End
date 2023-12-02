package example.entity.inner;

import com.fasterxml.jackson.annotation.JsonProperty;
import example.entity.response.father.Response;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @description: 用于数字相加的验证码实体类
 * @author: HammerRay
 * @date: 2023/11/18 下午5:38
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("数字验证码")
public class DigitOperaCode extends Response {
    /**
     * 验证码的uuid
     */
    @ApiModelProperty("验证码的Uuid")
    @JsonProperty("vcId")
    private String vcId;
    /**
     * 运算的结果
     */
    private int result;
    /**
     * 运算的式子
     *
     */
    @ApiModelProperty("运算的式子 比如 5 + 6 = ？")
    @JsonProperty("operationFormula")
    private String operationFormula;
    /**
     * operationFormula＋上图片 之和的base64编码
     */
    @ApiModelProperty("base64编码的图片的字符串")
    @JsonProperty("base64Img")
    private String base64Img;

    @Override
    public String toString() {
        return operationFormula + result;
    }
}
