package example.service;

import common.encrypt.PasswordEncrypt;
import common.verify.RegexValidator;
import example.entity.database.User;
import example.entity.database.VerifyCode;
import example.entity.inner.UniqueTypes;
import example.entity.request.findPasswd.FindPasswdRequest;
import example.entity.request.findPasswd.SecAnswerRequest;
import example.entity.response.CheckExistResponse;
import example.entity.response.UuidResponse;
import example.mapper.UserMapper;
import example.mapper.VerifyCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import response.ReBody;
import response.RepCode;

/**
 * @description: 找回密码服务
 * @author: HammerRay
 * @date: 2023/11/23 下午8:54
 */
@Service
public class FindPasswordService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    VerifyCodeMapper verifyCodeMapper;

    public ReBody checkExist(String string) {
        User user = new User();
        ReBody reBody =new ReBody();
        CheckExistResponse response = new CheckExistResponse();
        if (RegexValidator.regexEmail(string)) {
            user.setUserSysEmail(string);
            user = userMapper.selectOne(user);
            if(user == null){
                    reBody.setCode(RepCode.R_Error.getCode());
                    reBody.setMsg("邮箱未注册！请检查邮箱是否正确");
                    return reBody;
            }
            return getReBody(user, reBody, response);
        }
        if (RegexValidator.regexTelephone(string)) {
            user.setUserTelephone(string);
            user = userMapper.selectOne(user);
            if(user == null){
                reBody.setCode(RepCode.R_Error.getCode());
                reBody.setMsg("电话号码未注册！请检查电话号码是否正确");
                return reBody;
            }
            return getReBody(user, reBody, response);
        }

        user.setUserName(string);
        user = userMapper.selectOne(user);
        if(user == null){
            reBody.setCode(RepCode.R_Error.getCode());
            reBody.setMsg("用户名不存在！请检查用户名是否正确");
            return reBody;
        }
        return getReBody(user, reBody, response);
    }

    private ReBody getReBody(User user, ReBody reBody, CheckExistResponse response) {
        response.setUserTelephone(user.getUserTelephone());
        response.setUserName(user.getUserName());
        response.setUserSysEmail(user.getUserSysEmail());
        reBody.setData(response);
        reBody.setMsg("请求成功");
        reBody.setCode(RepCode.R_Ok.getCode());

        return reBody;
    }

    public UniqueTypes checkWhichOne(String string) {

        if (RegexValidator.regexEmail(string)) {
            return UniqueTypes.EMAIL;
        }
        if (RegexValidator.regexTelephone(string)) {

            return UniqueTypes.TELEPHONE;
        }
        return UniqueTypes.USERNAME;
    }

    public boolean checkProperSec(SecAnswerRequest request) {
        UniqueTypes uniqueTypes = checkWhichOne(request.getString());
        User user = new User();
        switch (uniqueTypes) {
            case EMAIL:
                user.setUserSysEmail(request.getString());
                break;
            case USERNAME:
                user.setUserName(request.getString());
                break;
            case TELEPHONE:
                user.setUserTelephone(request.getString());
                break;
            default:
                return false;
        }
        user = userMapper.selectOne(user);
        if (user == null) {
            return false;
        }
        if(user.getUserSecAnswer1() == null){
            if(user.getUserSecAnswer2() == null){
                if (user.getUserSecAnswer3() == null) {
                    return false;
                }else {
                    return  user.getUserSecAnswer3().equals(request.getUserSecAnswer3());
                }
            }else {
                if (user.getUserSecAnswer3() == null) {
                    return user.getUserSecAnswer2().equals(request.getUserSecAnswer2());
                }else {
                    return  user.getUserSecAnswer2().equals(request.getUserSecAnswer2()) &&
                            user.getUserSecAnswer3().equals(request.getUserSecAnswer3());
                }
            }
        }else {
            if(user.getUserSecAnswer2() == null){
                if (user.getUserSecAnswer3() == null) {
                    return user.getUserSecAnswer1().equals(request.getUserSecAnswer1());
                }else {
                    return  user.getUserSecAnswer1().equals(request.getUserSecAnswer1()) &&
                            user.getUserSecAnswer3().equals(request.getUserSecAnswer3());
                }
            }else {
                if (user.getUserSecAnswer3() == null) {
                    return user.getUserSecAnswer1().equals(request.getUserSecAnswer1()) &&
                            user.getUserSecAnswer2().equals(request.getUserSecAnswer2());
                }else {
                    return  user.getUserSecAnswer1().equals(request.getUserSecAnswer1()) &&
                            user.getUserSecAnswer2().equals(request.getUserSecAnswer2()) &&
                            user.getUserSecAnswer3().equals(request.getUserSecAnswer3());
                }
            }
        }


    }

    public boolean checkProperVal(String vcId, String validation) {
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(vcId);
        verifyCode = verifyCodeMapper.selectOne(verifyCode);

        if(verifyCode.getVcTelephoneCode() == null){
            if(verifyCode.getVcEmailCode() == null){
                return false;
            }else {
                return verifyCode.getVcEmailCode().equals(validation);
            }
        }
        return verifyCode.getVcEmailCode().equals(validation);
    }

    public UuidResponse findPasswd(FindPasswdRequest request) {
        User user = new User();
        UuidResponse uuidResponse = new UuidResponse();
        if(request.getNewPasswd().equals(request.getTwiceNewPasswd())){
            user.setUserSysEmail(request.getUserSysEmail());
            user.setUserTelephone(request.getUserTelephone());
            user.setUserName(request.getUserName());
            user = userMapper.selectOne(user);
            user.setUserPassword(PasswordEncrypt.hashPassword(request.getNewPasswd()));
            userMapper.update(user);
            uuidResponse.setUuid(user.getUserId());
            uuidResponse.setCode(200);
            uuidResponse.setMsg("修改成功!");
            return uuidResponse;
        }
        uuidResponse.setCode(500);
        uuidResponse.setMsg("两次输入的密码不一致！请重新输入");
        return uuidResponse;
    }

}
