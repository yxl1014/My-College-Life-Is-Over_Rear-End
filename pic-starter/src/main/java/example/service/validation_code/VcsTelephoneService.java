package example.service.validation_code;

import example.entity.database.VerifyCode;
import example.mapper.VerifyCodeMapper;
import example.service.validation_code.entity.StringCode;
import example.tools.RegexValidator;
import example.tools.UuidGenerator;
import example.tools.VerifyCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/17 下午11:37
 */
@Service
public class VcsTelephoneService {
    @Autowired
    VerifyCodeMapper verifyCodeMapper;

    public StringCode codeSend(String telephone) {
        // 使用substring方法去除字符串两端的双引号
        if (telephone.startsWith("\"") && telephone.endsWith("\"")) {
            telephone = telephone.substring(1, telephone.length() - 1);
        }

        if(!RegexValidator.regexTelephone(telephone)){
            throw new RuntimeException("电话号码格式不正确");
        }

        //TODO: 发送验证码到手机的 代码
        StringCode res = VerifyCodeGenerator.pureDigitCode();

        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(res.getVcId());
        verifyCode.setVcTelephone(telephone);
        verifyCode.setVcTelephoneCode(res.getValidation());
        verifyCode.setVcOperationType((short) 1);
        verifyCode.setVcCreateTime(new Timestamp(System.currentTimeMillis()));
        verifyCodeMapper.insert(verifyCode);
        return res;
    }



}
