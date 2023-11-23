package example.service.validation_code;

import example.service.validation_code.entity.DigitalOperationCode;
import example.tools.VerifyCodeGenerator;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * @description: 图片验证码
 * @author: HammerRay
 * @date: 2023/11/17 下午11:38
 */
@Service
public class VcsPictureService {


    /**
     * 数字验证码
     * @returnType: String
     * @author: GodHammer
     * @date: 2023-11-18 下午8:52
     * @version: v1.0
     */public String codeSendPureDigit() {
        //在此可自定义发送什么类型的验证码  可以是 可以是数字字母混合验证码  通过VerifyCodeGenerator可以自动生成

        //默认的 图片是6位数字的验证码
        return VerifyCodeGenerator.pureDigitCode();
    }

    /**
     * 6位字母验证码
     * @returnType: String
     * @author: GodHammer
     * @date: 2023-11-18 下午8:53
     * @version: v1.0
     */
    public String codeSendPureLetter(){
        return VerifyCodeGenerator.pureLetterCode();
    }

    /**
     * 6位数字字母混合验证码
     * @returnType: String
     * @author: GodHammer
     * @date: 2023-11-18 下午8:53
     * @version: v1.0
     */
    public String codeSendDigitLetter(){
        return VerifyCodeGenerator.digitLetterCode();
    }

    /**
     * 数字运算验证码
     * @returnType: String
     * @author: GodHammer
     * @date: 2023-11-18 下午8:54
     * @version: v1.0
     */
    public DigitalOperationCode codeSendDigitOpera(){

        return VerifyCodeGenerator.digitalOperationCode(new Random().nextInt(100));
    }
}
