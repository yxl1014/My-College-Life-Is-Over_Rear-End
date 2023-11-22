package example.service.verifycode;

import example.tools.RegexValidator;
import example.tools.VerifyCodeGenerator;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/17 下午11:37
 */
@Service
public class VcsTelephone {



    public String codeSend(String telephone) {
        telephone = "13466668888";
        if(!RegexValidator.regexTelephone(telephone)){
            throw new RuntimeException("电话号码格式不正确");
        }
        String code = VerifyCodeGenerator.pureDigitCode();

        //TODO: 发送验证码到手机的 代码

        return code;
    }



}
