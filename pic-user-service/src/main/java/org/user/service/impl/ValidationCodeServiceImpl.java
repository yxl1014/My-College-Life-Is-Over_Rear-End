package org.user.service.impl;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.util.Strings;
import org.commons.common.uuid.UuidGenerator;
import org.commons.common.verify.RegexValidator;
import org.commons.domain.constData.MagicMathConstData;
import org.commons.domain.constData.RedisConstData;
import org.commons.log.LogComp;
import org.commons.log.LogType;
import org.commons.response.ReBody;
import org.commons.response.RepCode;
import org.database.redis.RedisComp;
import org.springframework.stereotype.Service;
import org.user.entity.inner.DigitOperaCode;
import org.user.entity.request.VerityRequest;
import org.user.entity.response.VerityCodeResponse;
import org.user.service.IValidationCodeService;
import org.user.service.common.MailSendComp;
import org.user.tools.VerifyCodeGenerator;

/**
 * @author yxl17
 * @Package : org.user.service.impl
 * @Create on : 2023/12/25 22:23
 **/
@Service
public class ValidationCodeServiceImpl implements IValidationCodeService {
    private final Logger logger = LogComp.getLogger(ValidationCodeServiceImpl.class);

    private final MailSendComp mailSendComp;

    private final RedisComp redisComp;

    public ValidationCodeServiceImpl(MailSendComp mailSendComp, RedisComp redisComp) {
        this.mailSendComp = mailSendComp;
        this.redisComp = redisComp;
    }

    @Override
    public ReBody genMathVerityCode() {
        DigitOperaCode code = VerifyCodeGenerator.digitalOperationCode();
        redisComp.setex(RedisConstData.VERITY_PICTURE_CODE + code.getUuid(), code.getResult(), MagicMathConstData.MAGIC_VERITY_CODE_TIME);
        //TODO YXL 这个是测试用的 要删
        System.out.println(code.getResult());
        VerityCodeResponse verityCodeResponse = new VerityCodeResponse();
        verityCodeResponse.setBase64Img(code.getBase64Img());
        verityCodeResponse.setUuid(code.getUuid());
        ReBody reBody = new ReBody(RepCode.R_Ok);
        reBody.setData(verityCodeResponse);
        return reBody;
    }

    @Override
    public ReBody genTelVerityCode(String telephone) {
        if (!RegexValidator.regexTelephone(telephone)) {
            return new ReBody(RepCode.R_ParamError);
        }
        // 判断冷却时间
        String timeout = redisComp.get(RedisConstData.VERITY_TIMEOUT + telephone);
        if (!Strings.isEmpty(timeout)) {
            return new ReBody(RepCode.R_TooFast);
        }

        //TODO 发送验证码到手机的 代码
        String code = VerifyCodeGenerator.genNumberVerityCode();
        String uuid = UuidGenerator.getCustomUuid();

        redisComp.setex(RedisConstData.VERITY_TEL_CODE + uuid, code, MagicMathConstData.MAGIC_VERITY_CODE_TIME);
        redisComp.setex(RedisConstData.VERITY_TEL + uuid, telephone, MagicMathConstData.MAGIC_VERITY_CODE_TIME);

        //设置冷却时间
        redisComp.setex(RedisConstData.VERITY_TIMEOUT + telephone, MagicMathConstData.MAGIC_REDIS_TIMEOUT,
                MagicMathConstData.MAGIC_VERITY_CODE_TIME);

        VerityCodeResponse verityCodeResponse = new VerityCodeResponse();
        verityCodeResponse.setUuid(uuid);
        verityCodeResponse.setCode(code);
        ReBody reBody = new ReBody(RepCode.R_Ok);
        reBody.setData(verityCodeResponse);
        return reBody;
    }

    @Override
    public ReBody genEmailVerityCode(String recipientAddr) {
        if (!RegexValidator.regexEmail(recipientAddr)) {
            return new ReBody(RepCode.R_ParamError);
        }
        // 判断冷却时间
        String timeout = redisComp.get(RedisConstData.VERITY_TIMEOUT + recipientAddr);
        if (!Strings.isEmpty(timeout)) {
            return new ReBody(RepCode.R_TooFast);
        }

        String code = VerifyCodeGenerator.genNumberVerityCode();

        try {
            mailSendComp.sendEmailVerityCode(recipientAddr, code);
        } catch (Exception e) {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.UTIL);
            logMessage.build("发送邮件抛错", e.getMessage());
            logger.error(logMessage.log());
            return new ReBody(RepCode.R_EmailNotFound);
        }
        String uuid = UuidGenerator.getCustomUuid();
        //存redis
        redisComp.setex(RedisConstData.VERITY_EMAIL_CODE + uuid, code, MagicMathConstData.MAGIC_VERITY_CODE_TIME);
        redisComp.setex(RedisConstData.VERITY_EMAIL + uuid, recipientAddr, MagicMathConstData.MAGIC_VERITY_CODE_TIME);

        //设置冷却时间
        redisComp.setex(RedisConstData.VERITY_TIMEOUT + recipientAddr, MagicMathConstData.MAGIC_REDIS_TIMEOUT,
                MagicMathConstData.MAGIC_VERITY_CODE_TIME);

        //TODO YXL 这个是测试用的 要删
        System.out.println(code);

        VerityCodeResponse verityCodeResponse = new VerityCodeResponse();
        verityCodeResponse.setUuid(uuid);
        ReBody reBody = new ReBody(RepCode.R_Ok);
        reBody.setData(verityCodeResponse);
        return reBody;
    }

    @Override
    public ReBody verityCode(VerityRequest verityRequest, int type) {
        if (verityRequest == null || Strings.isEmpty(verityRequest.getUuid()) || Strings.isEmpty(verityRequest.getCode())) {
            return new ReBody(RepCode.R_ParamNull);
        }
        String prefix = getVerityRedisPrefix(type);
        if (prefix == null) {
            LogComp.LogMessage logMessage = LogComp.buildData(LogType.SYSTEM);
            logMessage.build("这个type不可能空,type", type);
            logger.error(logMessage.log());
            return new ReBody(RepCode.R_WhyNull);
        }
        String code = redisComp.get(prefix + verityRequest.getUuid());
        if (code == null) {
            return new ReBody(RepCode.R_CodeExpire);
        }
        if (!verityRequest.getCode().equals(code)) {
            return new ReBody(RepCode.R_CodeError);
        }
        return new ReBody(RepCode.R_Ok);
    }

    private String getVerityRedisPrefix(int type) {
        switch (type) {
            case 1:
                return RedisConstData.VERITY_PICTURE_CODE;
            case 2:
                return RedisConstData.VERITY_TEL_CODE;
            case 3:
                return RedisConstData.VERITY_EMAIL_CODE;
            default:
                return null;
        }
    }
}
