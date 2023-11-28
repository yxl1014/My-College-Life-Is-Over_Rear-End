package example.service.validation_code;

import example.entity.database.VerifyCode;
import example.mapper.VerifyCodeMapper;
import example.service.validation_code.entity.DigitalOperationCode;
import example.service.validation_code.entity.StringCode;
import example.tools.VerifyCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Random;

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
     */public StringCode codeSendPureDigit() {
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
    public StringCode codeSendPureLetter(){
        return VerifyCodeGenerator.pureLetterCode();
    }

    /**
     * 6位数字字母混合验证码
     * @returnType: StringCode
     * @author: GodHammer
     * @date: 2023-11-18 下午8:53
     * @version: v1.0
     */
    public StringCode codeSendDigitLetter(){
        return VerifyCodeGenerator.digitLetterCode();
    }

    /**
     * 数字运算验证码
     * @returnType: StringCode
     * @author: GodHammer
     * @date: 2023-11-18 下午8:54
     * @version: v1.0
     */
    public DigitalOperationCode codeSendDigitOpera(){
        //TODO 写入数据库中
        DigitalOperationCode digitalOperationCode = VerifyCodeGenerator.digitalOperationCode();
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(digitalOperationCode.getVcId());
        verifyCode.setVcPictureCode(digitalOperationCode.toString());
        verifyCode.setVcCreateTime(new Timestamp(System.currentTimeMillis()));
        verifyCodeMapper.insert(verifyCode);
        return digitalOperationCode;
    }
}
