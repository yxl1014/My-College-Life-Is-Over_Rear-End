package org.commons.common.verify;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @description: 正则表达式验证
 * @author: HammerRay
 * @date: 2023/11/14 上午9:22
 */
public class RegexValidator {

    /**
     * 邮箱格式的regex
     */
    public static final String EMAIL_FM = "一个或多个用户名@一个或多个域名 比如 nnn.aaa@kkk.edu.cn";
    /**
     * 手机号码格式的regex
     */
    public static final String TELEPHONE_FM = "第一位是1，第二位是3-9之间的数字，后面九位都是0-10位的数字，一共11位手机号码";
    /**
     * 用户名格式的regex
     */
    public static final String USERNAME_FM = "2到10个汉字长 或 字母+数字+_组成的3到16位字符长";
    /**
     * 高强度密码格式的regex
     */
    public static final String PASSWD_HFM = "至少包括一个大写字母、一个小写字母、一个数字和一个特殊符号的6到16位字符长";
    /**
     * 中强度密码格式的regex
     */
    public static final String PASSWD_MFM = "至少包括一个大写字母、一个小写字母、一个数字的，6到16位字符长";
    /**
     * 低强度密码格式的regex
     */
    public static final String PASSWD_LFM = "6到16位字符";


    public static boolean regexEmail(String email){
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        //                                 一个或多个用户名            tute.edu. 一个或多个域名
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    public static boolean regexTelephone(String telephone){
        String regex = "^1[3-9]\\d{9}$";
        // 手机号码第一位是1 第二位是3-9之间的数字，后面加上9位的每一位都可以任意的整数数字.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(telephone);
        return matcher.matches();
    }
    public static boolean regexUsername(String username){
        String regex = "^[a-zA-Z0-9_]{3,16}$";
        // 用户名 是由字母+数字＋下划线组成的，必须要[3,16]个字符.
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean regexUsernameChinese(String username){
        String regex= "\"^[\\\\u4e00-\\\\u9fa5]{2,10}$\"";
        //使用unicode的编码范围 来规范汉字的用户名 u4e00是Unicode编码的汉字字符集的第一个字符 u9fa5是最后一个  u9fa5 - u4e00 = 40917 - 19968 = 20949
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(username);
        return matcher.matches();
    }

    public static boolean isStrongPasswd(String password) {
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[!@#$%^&*()=+{};':<>,./?`~-])[A-Za-z\\d!@#$%^&*()=+{};':<>,./?`~-]{6,16}$";
        // 该正则表达式要求包含至少一个大写字母、一个小写字母、一个数字和一个特殊字符，总长度在6到16个字符之间。
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public static boolean isMediumPasswd(String passwd){
        String regex = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)[A-Za-z\\d]{6,16}$";
        // 该正则表达式要求包含至少一个大写字母、一个小写字母、一个数字，总长度在6到16个字符之间。
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(passwd);
        return matcher.matches();
    }

    public static boolean isLowPasswd(String passwd){
        String regex = "^.{6,16}$";
        // 该正则表达式要求包含6到16个字符长度的 字符串就行
        Pattern patten = Pattern.compile(regex);
        Matcher matcher = patten.matcher(passwd);
        return matcher.matches();
    }

    public static void main(String[] args) {
        String password = "SecureP@ss1"; // 替换成你要检验的密码
        boolean isStrong = isLowPasswd(password);

        if (isStrong) {
            System.out.println("密码强度合格！");
        } else {
            System.out.println("密码强度不合格，请遵循密码规则。");
        }
    }
}
