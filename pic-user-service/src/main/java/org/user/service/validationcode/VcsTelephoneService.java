package org.user.service.validationcode;

import org.commons.common.verify.RegexValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.user.entity.response.StringCodeResponse;
import org.user.tools.VerifyCodeGenerator;

/**
 * @description:
 * @author: HammerRay
 * @date: 2023/11/17 下午11:37
 */
@Service
public class VcsTelephoneService {
    @Autowired
    ValidationCacheService validationCacheService;

    public StringCodeResponse codeSend(String telephone) {
        // 使用substring方法去除字符串两端的双引号
        if (telephone.startsWith("\"") && telephone.endsWith("\"")) {
            telephone = telephone.substring(1, telephone.length() - 1);
        }

        if (!RegexValidator.regexTelephone(telephone)) {
            throw new RuntimeException("电话号码格式不正确");
        }

        //TODO: 发送验证码到手机的 代码
        StringCodeResponse res = VerifyCodeGenerator.pureDigitCode();
        validationCacheService.cacheCode(res.getVcId(), res.getValidation());

        res.setCode(200);
        res.setMsg("发送成功!验证码有效时长为60s");
        return res;
    }


}
