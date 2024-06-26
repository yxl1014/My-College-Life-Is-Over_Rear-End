package org.commons.domain.inner;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @description: 用于数字相加的验证码实体类
 * @author: HammerRay
 * @date: 2023/11/18 下午5:38
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("数字验证码")
public class DigitOperaCode{
    /**
     * 验证码的uuid
     */
    @ApiModelProperty("验证码的Uuid")
    private String uuid;
    /**
     * 运算的结果
     */
    private int result;
    /**
     * 运算的式子
     *
     */
    @ApiModelProperty("运算的式子 比如 5 + 6 = ？")
    private String operationFormula;
    /**
     * operationFormula＋上图片 之和的base64编码
     */
    @ApiModelProperty("base64编码的图片的字符串")
    private String base64Img;

    @Override
    public String toString() {
        return operationFormula + result;
    }
}
