package example.service;

import common.encrypt.PasswordEncrypt;
import common.verify.RegexValidator;
import example.entity.database.User;
import example.entity.database.VerifyCode;
import example.entity.request.LoginRequest;
import example.entity.response.UuidResponse;
import example.mapper.UserMapper;
import example.mapper.VerifyCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 *  如何动态地获取各个系统定义的用户类?
 * 登录服务类
 * @author: HammerRay
 * @date: 2023/11/4 下午11:19
 */
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    VerifyCodeMapper verifyCodeMapper;



    public UuidResponse login(Object object){
        LoginRequest loginRequest =(LoginRequest)object;
        User user = new User();
        VerifyCode verifyCode =new VerifyCode();
        UuidResponse uuidResponse = new UuidResponse();

        if(loginRequest.getUserName() .isEmpty()){

            if(loginRequest.getUserTelephone().isEmpty()){
                //邮箱验证码登录
                user.setUserSysEmail(loginRequest.getUserSysEmail());

                if(loginRequest.getVcEmailCode().isEmpty()){
                    uuidResponse.setCode(500);
                    uuidResponse.setMsg("请输入邮箱里收到的验证码");
                    return uuidResponse;
                }
                verifyCode.setVcId(loginRequest.getVcEmailCode().getVcId());
                verifyCode.setVcEmailCode(loginRequest.getVcEmailCode().getValidation());

                VerifyCode tmp = verifyCodeMapper.selectOne(verifyCode);

                if(tmp.getVcEmailCode().equals(loginRequest.getVcEmailCode().getValidation())){
                    if(tmp.getVcId().equals(loginRequest.getVcEmailCode().getVcId())){
                        uuidResponse.setMsg("登录成功！");
                        uuidResponse.setCode(200);
                        uuidResponse.setUuid(userMapper.selectOne(user).getUserId());
                    }

                }else {
                    uuidResponse.setMsg("登录失败！验证码错误");
                    uuidResponse.setCode(200);
                }
                return uuidResponse;


            }else {
                //电话验证码登录
                user.setUserTelephone(loginRequest.getUserTelephone());

                if(loginRequest.getVcTelephoneCode().isEmpty()){
                    uuidResponse.setCode(500);
                    uuidResponse.setMsg("请输入手机上收到的验证码");
                    return uuidResponse;
                }
                verifyCode.setVcId(loginRequest.getVcTelephoneCode().getVcId());
                verifyCode.setVcTelephoneCode(loginRequest.getVcTelephoneCode().getValidation());

                uuidResponse.setCode(200);
                uuidResponse.setMsg("手机验证码方式，登录成功");
                uuidResponse.setUuid(userMapper.selectOne(user).getUserId());
                return uuidResponse;
            }
        }else {

            user.setUserName(loginRequest.getUserName());
            user.setUserPassword(loginRequest.getUserPassword());

            if(loginRequest.getVcPictureCode().isEmpty()){
                uuidResponse.setCode(500);
                uuidResponse.setMsg("请输入图片上的验证码");
                return uuidResponse;
            }
            verifyCode.setVcId(loginRequest.getVcPictureCode().getVcId());
            verifyCode.setVcPictureCode(String.valueOf(loginRequest.getVcPictureCode().getResult()));

            if(!RegexValidator.isLowPasswd(user.getUserPassword())){
                uuidResponse.setCode(500);
                uuidResponse.setMsg("errorMsg: 登录密码格式错误,正确："+RegexValidator.PASSWD_LFM);
                return uuidResponse;
            }

            User user1 =userMapper.selectOne(user);

            if(user1 == null){
                uuidResponse.setCode(500);
                uuidResponse.setMsg("用户不存在，请检查账号是否正确");
                return uuidResponse;
            }else {


                if(Objects.equals(PasswordEncrypt.hashPassword(user.getUserPassword()), user1.getUserPassword())){

                    VerifyCode tmp = verifyCodeMapper.selectOne(verifyCode);
                    if(tmp.getVcPictureCode().equals(String.valueOf(loginRequest.getVcPictureCode().getResult()))){
                        uuidResponse.setCode(200);
                        uuidResponse.setMsg("登录成功");
                        uuidResponse.setUuid(user1.getUserId());
                        return uuidResponse;
                    }else {
                        uuidResponse.setCode(500);
                        uuidResponse.setMsg("登录失败,验证码出错");
                        return uuidResponse;
                    }


                }else {
                    uuidResponse.setCode(500);
                    uuidResponse.setMsg("密码错误，请检查密码是否正确");
                    return uuidResponse;
                }
            }

        }



            //读取配置文件中：用户的登录类
            //创建配置文件
            // -->读取配置文件，用户类的完整路径比如:org.example.entity.database.User
            // -->如何把类搬到这里?  即@SelfDefine User user标识一下，加个配置文件配置可以动态的改变
            //做到打成jar包 在另一个系统使用的时候配置一下 数据库源 和 这个类的路径 就可以使用



    }
    
    /**
     * description: 验证字符串的正确性通过比较每一个字符
     * @paramType [java.lang.String, java.lang.String]
     * @param var1:
     * @param var2:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-11-23 下午8:49
     */
    public boolean stringValidationVerify(String var1, String var2){
        return true;
    }
    /**
     * description: 验证数字运算的验证码的正确性，通过运算的结果
     * @paramType [java.lang.Integer, java.lang.Integer]
     * @param var1:
     * @param var2:
     * @returnType: boolean
     * @author: GodHammer
     * @date: 2023-11-23 下午8:49
     */
    public boolean integerValidationVerify(Integer var1,Integer var2){
        return true;
    }
}
