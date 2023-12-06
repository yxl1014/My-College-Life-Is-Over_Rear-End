package example.service.validationCode;

import example.entity.database.VerifyCode;
import example.entity.inner.DigitOperaCode;
import example.mapper.VerifyCodeMapper;
import example.entity.response.DigitOperaCodeResponse;
import example.entity.response.StringCodeResponse;
import example.tools.VerifyCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @description: 图片验证码
 * @author: HammerRay
 * @date: 2023/11/17 下午11:38
 */
@Service
public class VcsPictureService {
    @Autowired
    VerifyCodeMapper verifyCodeMapper;

    /**
     * 数字验证码
     * @returnType: StringCode
     * @author: GodHammer
     * @date: 2023-11-18 下午8:52
     * @version: v1.0
     */public StringCodeResponse codeSendPureDigit() {
        //在此可自定义发送什么类型的验证码  可以是 可以是数字字母混合验证码  通过VerifyCodeGenerator可以自动生成

        //默认的 图片是6位数字的验证码
        return VerifyCodeGenerator.pureDigitCode();
    }

    /**
     * 6位字母验证码
     * @returnType: StringCode
     * @author: GodHammer
     * @date: 2023-11-18 下午8:53
     * @version: v1.0
     */
    public StringCodeResponse codeSendPureLetter(){
        return VerifyCodeGenerator.pureLetterCode();
    }

    /**
     * 6位数字字母混合验证码
     * @returnType: StringCode
     * @author: GodHammer
     * @date: 2023-11-18 下午8:53
     * @version: v1.0
     */
    public StringCodeResponse codeSendDigitLetter(){
        return VerifyCodeGenerator.digitLetterCode();
    }

    /**
     * 数字运算验证码
     * @returnType: StringCode
     * @author: GodHammer
     * @date: 2023-11-18 下午8:54
     * @version: v1.0
     */
    public DigitOperaCodeResponse codeSendDigitOpera(){

        DigitOperaCode digitOperaCode= VerifyCodeGenerator.digitalOperationCode();
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(digitOperaCode.getVcId());
        verifyCode.setVcPictureCode(String.valueOf(digitOperaCode.getResult()));
        verifyCode.setVcCreateTime(new Timestamp(System.currentTimeMillis()));

        verifyCodeMapper.insert(verifyCode);
        DigitOperaCodeResponse digitOperaCodeResponse = new DigitOperaCodeResponse();
        digitOperaCodeResponse.setBase64Img(digitOperaCode.getBase64Img());
        digitOperaCodeResponse.setVcId(digitOperaCode.getVcId());
        digitOperaCodeResponse.setCode(200);
        digitOperaCodeResponse.setMsg("返回成功");
        return digitOperaCodeResponse;
    }
}
