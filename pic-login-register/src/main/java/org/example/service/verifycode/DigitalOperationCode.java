package org.example.service.verifycode;

import lombok.Data;

/**
 * @description:
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
