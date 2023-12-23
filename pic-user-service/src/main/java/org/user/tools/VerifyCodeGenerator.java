package org.user.tools;


import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.codec.Base64;
import cn.hutool.core.img.ImgUtil;
import org.commons.common.uuid.UuidGenerator;
import org.springframework.util.FastByteArrayOutputStream;
import org.user.entity.inner.DigitOperaCode;
import org.user.entity.response.StringCodeResponse;

import java.util.Random;

/**
 * 数字验证码  字符串验证码
 *
 * @description:
 * @author: HammerRay
 * @date: 2023/11/17 下午10:56
 */
public class VerifyCodeGenerator {

    /**
     * 验证码位数
     */
    public static int CODE_BITS = 6;
    /**
     * 默认一次运算 即 6 + 7 = 13 一个＋号
     */
    public static int OPERA_TIMES = 1;

    /**
     * 要求result传参必须为100以内0以上的整数
     *
     * @paramType: int
     * @returnType: String
     * @author: GodHammer
     * @date: 2023-11-18 上午6:49
     * @version: v1.0
     */
    public static DigitOperaCode digitalOperationCode() {
        int result = new Random().nextInt(100) + 1;
        DigitOperaCode res = new DigitOperaCode();
        res.setVcId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());

        res.setResult(result);
        //这个结果可以等于多少-多少  也可以等于多少＋多少  不考虑后两个：也可以等于多少*多少  也可以等于多少/多少  这个验证码的作用是检查是否真人在操作，乘除可能有人不会。
        StringBuilder code = new StringBuilder();
        for (int i = 0; i < OPERA_TIMES; i++) {
            int tmp = new Random().nextInt(result);
            code.append(tmp);
            code.append('+');
            result -= tmp;
        }
        code.append(result);
        code.append('=');
        code.append('?');

        res.setOperationFormula(code.toString());
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
        ImgUtil.writePng(lineCaptcha.createImage(code.toString()), os);

        res.setBase64Img(Base64.encode(os.toByteArray()));

//        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
//        String verify = IdUtil.simpleUUID();
//        //图形验证码写出，可以写出到文件，也可以写出到流
//        FastByteArrayOutputStream os = new FastByteArrayOutputStream();
//        lineCaptcha.write(os);
//        String code = lineCaptcha.getCode();
////        缓存一分钟的验证码
//        redisTemplate.opsForValue().set(verify, code, Duration.ofMinutes(1));
//        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(5);
//        //验证码对应的redis上的uuid
//        map.put("uuid", verify);
//        //图片上的验证码
//        map.put("code", code);
//        //将图片转换成输出流传到前端上
//        map.put("img",Base64.encode(os.toByteArray()));

        return res;
    }

    public static StringCodeResponse digitLetterCode() {
        StringCodeResponse res = new StringCodeResponse();
        res.setVcId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        //数字字母混合六位验证码
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_BITS; i++) {
            int tmp = new Random().nextInt(9);
            if (tmp % 2 == 0) {

                //大写英文字母
                char tmp1 = (char) ((char) new Random().nextInt(25) + 65);
                //字母是偶数才给字符串拼追字符
                if (tmp1 % 2 == 0) {
                    code.append(tmp1);
                } else {
                    code.append(tmp);
                }
            } else {
                //小写英文字母
                char tmp2 = (char) ((char) new Random().nextInt(25) + 97);
                //字母是奇数才给字符串追拼字符
                if (tmp2 % 2 != 0) {
                    code.append(tmp2);
                } else {
                    code.append(tmp);
                }
            }
        }

        res.setValidation(code.toString());
        return res;
    }

    public static StringCodeResponse pureDigitCode() {
        StringCodeResponse res = new StringCodeResponse();
        res.setVcId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        //纯数字6位验证码
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < (CODE_BITS / 2); i++) {
            int tmp = new Random().nextInt(90) + 10;
            code.append(tmp);
        }
        res.setValidation(code.toString());
        return res;
    }

    public static StringCodeResponse pureLetterCode() {
        StringCodeResponse res = new StringCodeResponse();
        res.setVcId(UuidGenerator.getCustomUuid(System.currentTimeMillis()).toString());
        //纯字母6位验证码
        StringBuilder code = new StringBuilder();

        for (int i = 0; i < CODE_BITS; i++) {
            int tmp = new Random().nextInt(9);
            if (tmp % 2 == 0) {
                //大写英文字母
                char tmp1 = (char) ((char) new Random().nextInt(25) + 65);
                code.append(tmp1);
            } else {
                //小写英文字母
                char tmp2 = (char) ((char) new Random().nextInt(25) + 97);
                code.append(tmp2);
            }

            code.append(tmp);
        }
        res.setValidation(code.toString());
        return res;
    }


    public static void main(String[] args) {
        char tmp1 = (char) ((char) new Random().nextInt(25) + 65);
        System.out.println(tmp1);
    }
}
