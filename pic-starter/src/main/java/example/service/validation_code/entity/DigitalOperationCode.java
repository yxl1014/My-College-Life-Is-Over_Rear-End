package example.service.validation_code.entity;

import lombok.Data;

/**
 * @description: 用于数字相加的验证码实体类
 * @author: HammerRay
 * @date: 2023/11/18 下午5:38
 */
@Data
public class DigitalOperationCode {
    /**
     * 运算的结果
     */
    private int result;
    /**
     * 运算的式子 比如 5 + 6 = ？
     */
    private String operationFormula;

    @Override
    public String toString() {
        return operationFormula+" res:"+result;
    }
}
