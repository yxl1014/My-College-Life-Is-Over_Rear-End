package example.service;

import example.entity.database.User;
import example.entity.database.VerifyCode;
import example.entity.request.FindPasswdRequest;
import example.entity.response.UuidResponse;
import example.mapper.UserMapper;
import example.mapper.VerifyCodeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

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

    public UuidResponse findPasswd(FindPasswdRequest request){
        short flag = request.getFlag();

        switch (flag){
            case 1:return usernameSecProblem1(request);
            case 2:return phoneSecProblem2(request);
            case 3:return emailSecProblem3(request);
            case 4:return phoneValidation4(request);
            case 5:return emailValidation5(request);
            default: return new UuidResponse(500,"找不到匹配的处理方法，flag错误",null);
        }
    }


    private UuidResponse usernameSecProblem1(FindPasswdRequest request){
        UuidResponse uuidResponse = new UuidResponse();
        User user = new User();
        user.setUserName(request.getUserName());

        user =userMapper.selectOne(user);
        if(!request.getUserSecAnswer1().equals(user.getUserSecAnswer1())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }
        if(!request.getUserSecAnswer2().equals(user.getUserSecAnswer2())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }
        if(!request.getUserSecAnswer3().equals(user.getUserSecAnswer3())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }

        user.setUserPassword(request.getNewPasswd());
        userMapper.update(user);
        uuidResponse.setCode(200);
        uuidResponse.setUuid(user.getUserId());
        uuidResponse.setMsg("找回密码成功");
        return uuidResponse;
    }
    private UuidResponse phoneSecProblem2(FindPasswdRequest request){
        UuidResponse uuidResponse = new UuidResponse();
        User user = new User();
        user.setUserTelephone(request.getUserTelephone());

        user =userMapper.selectOne(user);
        if(!request.getUserSecAnswer1().equals(user.getUserSecAnswer1())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }
        if(!request.getUserSecAnswer2().equals(user.getUserSecAnswer2())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }
        if(!request.getUserSecAnswer3().equals(user.getUserSecAnswer3())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }

        user.setUserPassword(request.getNewPasswd());
        userMapper.update(user);
        uuidResponse.setCode(200);
        uuidResponse.setUuid(user.getUserId());
        uuidResponse.setMsg("找回密码成功");
        return uuidResponse;
    }
    private UuidResponse emailSecProblem3(FindPasswdRequest request){
        UuidResponse uuidResponse = new UuidResponse();
        User user = new User();
        user.setUserSysEmail(request.getUserSysEmail());
        user =userMapper.selectOne(user);
        if(!request.getUserSecAnswer1().equals(user.getUserSecAnswer1())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }
        if(!request.getUserSecAnswer2().equals(user.getUserSecAnswer2())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }
        if(!request.getUserSecAnswer3().equals(user.getUserSecAnswer3())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("密保答案错误");
            return uuidResponse;
        }

        user.setUserPassword(request.getNewPasswd());
        userMapper.update(user);
        uuidResponse.setCode(200);
        uuidResponse.setUuid(user.getUserId());
        uuidResponse.setMsg("找回密码成功");
        return uuidResponse;
    }
    private UuidResponse phoneValidation4(FindPasswdRequest request){
        UuidResponse uuidResponse = new UuidResponse();
        ;
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(request.getVcTelephoneCode().getVcId());
        verifyCode = verifyCodeMapper.selectOne(verifyCode);


        if(!verifyCode.getVcTelephoneCode().equals(request.getVcTelephoneCode().getValidation())){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("验证码错误");
            return uuidResponse;
        }

        User user = new User();
        user.setUserTelephone(request.getUserTelephone());
        user =userMapper.selectOne(user);
        user.setUserPassword(request.getNewPasswd());
        userMapper.update(user);
        uuidResponse.setCode(200);
        uuidResponse.setUuid(user.getUserId());
        uuidResponse.setMsg("找回密码成功");
        return uuidResponse;
    }
    private UuidResponse emailValidation5(FindPasswdRequest request){
        UuidResponse uuidResponse = new UuidResponse();
        String vcEmailCodeId = request.getVcEmailCode().getVcId();
        VerifyCode verifyCode = new VerifyCode();
        verifyCode.setVcId(vcEmailCodeId);
        verifyCode = verifyCodeMapper.selectOne(verifyCode);

        if(!(verifyCode.getVcEmailCode().equals(request.getVcEmailCode().getValidation()))){
            uuidResponse.setCode(200);
            uuidResponse.setMsg("验证码错误");
            return uuidResponse;
        }
        User user = new User();
        user.setUserSysEmail(request.getUserSysEmail());
        user =userMapper.selectOne(user);
        user.setUserPassword(request.getNewPasswd());
        userMapper.update(user);
        uuidResponse.setCode(200);
        uuidResponse.setUuid(user.getUserId());
        uuidResponse.setMsg("找回密码成功");
        return uuidResponse;
    }
}
