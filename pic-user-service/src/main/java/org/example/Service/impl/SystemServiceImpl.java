package org.example.Service.impl;

import org.apache.logging.log4j.Logger;
import org.example.Service.ISystemService;
import org.example.common.random.RandomComp;
import org.example.common.verify.MathVerifyCodeComp;
import org.example.domain.MathVerityBody;
import org.example.log.LogComp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @author yxl17
 * @Package : org.example.Service.impl
 * @Create on : 2023/11/12 13:51
 **/

@Service
public class SystemServiceImpl implements ISystemService {
    private final static Logger log = LogComp.getLogger(SystemServiceImpl.class);

    private final MathVerifyCodeComp mathVerifyCodeComp;

    private final RandomComp randomComp;

    public SystemServiceImpl(MathVerifyCodeComp mathVerifyCodeComp, RandomComp randomComp) {
        this.mathVerifyCodeComp = mathVerifyCodeComp;
        this.randomComp = randomComp;
    }

    @Override
    public MathVerityBody getMathVerityBody() {
        // 1. 随机数据和运算符号
        Random random = randomComp.getRandom();
        int num1 = random.nextInt(10);
        int num2 = random.nextInt(10);
        int code = random.nextInt(4);
        int result = 0;
        String text = "";
        //TODO YXL 这个代码看的我有点恶心 <赵阳>看到了给他封一下
        switch (code) {
            case 0:
                result = num1 + num2;
                text += num1 + "+" + num2;
                break;
            case 1:
                result = num1 - num2;
                text += num1 + "-" + num2;
                break;
            case 2:
                result = num1 * num2;
                text += num1 + "*" + num2;
                break;
            case 3:
                // 被除数不能为0
                if (num2 == 0) {
                    num2 = 2;
                }
                // 确保他能被整除
                if (num1 % num2 != 0) {
                    num1 += num2 - (num1 % num2);
                }
                result = num1 / num2;
                text += num1 + "/" + num2;
                break;
            default:
                break;
        }
        // 2. 生成body
        MathVerityBody body = mathVerifyCodeComp.buildMathVerityBody(text);
        // 3. 存redis 有效期1分钟 key:"唯一前缀" + body.getUuid() value: result
        log.info(text + "=" + result);
        return body;
    }
}
