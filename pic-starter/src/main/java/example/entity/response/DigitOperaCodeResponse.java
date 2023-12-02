package example.entity.response;

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
@ApiModel("数字验证码响应")
public class DigitOperaCodeResponse extends Response {
    /**
     * 验证码的uuid
     */
    @ApiModelProperty("验证码的Uuid")
    @JsonProperty("vcId")
    private String vcId;

    /**
     * operationFormula＋上图片 之和的base64编码
     */
    @ApiModelProperty("base64编码的图片的字符串")
    @JsonProperty("base64Img")
    private String base64Img;


}
